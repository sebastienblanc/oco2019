/*
 * Copyright 2019 JF James.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sebjef.easystore.control.bank;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.sebjef.easystore.control.PaymentProcessingContext;
import org.sebjef.easystore.entity.ProcessingMode;

/**
 *
 * @author JF James
 */
@ApplicationScoped
@Log
public class BankAuthorService {

    @Inject
    @ConfigProperty(name = "payment.author.merchantId", defaultValue = "Devoxx Store")
    private String merchantId;

    @Inject
    @ConfigProperty(name = "payment.max.amount.fallback", defaultValue = "20000")
    private int maxAmountFallBack;
    
    // Injection not needed, just for debug purpose during the demo
    @Inject
    @ConfigProperty(name="org.sebjef.easystore.control.bank.BankAuthorClient/mp-rest/url")
    private String bankAuthorUrl;

    @Inject
    @RestClient
    private BankAuthorClient bankAuthorClient;

    private BankAuthorRequest initRequest(PaymentProcessingContext context) {
        BankAuthorRequest request = new BankAuthorRequest();
        request.setMerchantId(merchantId);
        request.setAmount(context.getAmount());
        request.setCardNumber(context.getCardNumber());
        request.setExpiryDate(context.getExpiryDate());
        return request;
    }

    @Retry(maxRetries = 1, delay = 200, maxDuration = 1000)
    @Fallback(fallbackMethod = "acceptByDelegation")
    public boolean authorize(PaymentProcessingContext context) {
        log.info("JJS => calling BankAthorService.authorize, bankAuthorUrl= " + bankAuthorUrl);
        try {
            BankAuthorResponse response = bankAuthorClient.authorize(initRequest(context));
            context.setBankCalled(true);
            context.setAuthorId(Optional.of(response.getAuthorId()));
            context.setAuthorized(response.isAuthorized());
            return context.isAuthorized();
        } catch (RuntimeException e) {
            // If empty endpoint ProcessingException: Error writing JSON-B serialized object
            log.warning("JJS => authorize exception " + e);
            throw e;
        }
    }

    // Fallback method must be public: with Payara @Timeout or @Retry needed to call it properly
    public boolean acceptByDelegation(PaymentProcessingContext context) {
        log.warning("JJS => calling acceptByDelegartion " + Thread.currentThread() + ", context " + context);
        context.setBankCalled(false);
        context.setProcessingMode(ProcessingMode.FALLBACK);
        return (context.getAmount() <= maxAmountFallBack);
    }

}
