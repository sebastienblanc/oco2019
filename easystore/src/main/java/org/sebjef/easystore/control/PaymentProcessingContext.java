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
package org.sebjef.easystore.control;

import org.sebjef.easystore.entity.PaymentResponseCode;
import java.time.LocalDateTime;
import java.util.Optional;
import org.sebjef.easystore.entity.ProcessingMode;
import lombok.Data;
import org.sebjef.easystore.boundary.PaymentRequest;
import org.sebjef.easystore.boundary.PaymentResponse;
import org.sebjef.easystore.entity.CardType;

/**
 *
 * @author JF James
 */
@Data
public class PaymentProcessingContext {

    // Data coming from the request
    private final String posId;
    private final String cardNumber;
    private final String expiryDate;
    private final int amount;

    // Data generated by the processing
    private long id;
    private PaymentResponseCode responseCode;
    private CardType cardType;
    private ProcessingMode processingMode;
    private long responseTime;
    private LocalDateTime dateTime;
    private boolean bankCalled;
    private boolean authorized;
    private Optional<Long> authorId;

    // Initializes the processing context from the request
    public PaymentProcessingContext(PaymentRequest request) {
        this.posId = request.getPosId();
        this.dateTime = LocalDateTime.now();
        this.responseTime = System.currentTimeMillis();
        this.cardNumber = request.getCardNumber();
        this.expiryDate = request.getExpiryDate();
        this.amount = request.getAmount();

        // Default values
        this.responseCode = PaymentResponseCode.ACCEPTED;
        this.processingMode = ProcessingMode.STANDARD;
        this.authorized = false;
        this.bankCalled = false;
        this.authorId = Optional.empty();
    }

    // Generates the response from the processing context
    public PaymentResponse generateResponse() {
        PaymentResponse response = new PaymentResponse();

        response.setPaymentId(id);
        response.setAmount(amount);
        response.setCardNumber(cardNumber);
        response.setCardType(cardType);
        response.setExpiryDate(expiryDate);
        response.setPosId(posId);
        response.setResponseCode(responseCode);
        response.setBankCalled(bankCalled);
        response.setAuthorized(authorized);

            response.setAuthorId(authorId);
        
        response.setResponseTime(responseTime);
        response.setProcessingMode(processingMode);

        return response;
    }

}
