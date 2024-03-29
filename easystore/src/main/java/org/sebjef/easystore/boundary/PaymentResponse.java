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
package org.sebjef.easystore.boundary;

import java.util.Optional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sebjef.easystore.entity.PaymentResponseCode;
import org.sebjef.easystore.entity.CardType;
import org.sebjef.easystore.entity.ProcessingMode;

/**
 *
 * @author JF James
 */
@Data
@NoArgsConstructor
public class PaymentResponse {

    // Data coming from the request
    private String posId;
    private String cardNumber;
    private String expiryDate;
    private int amount;

    // Data generated by the processing
    private long paymentId;
    private PaymentResponseCode responseCode;
    private Optional<Long> authorId;
    private CardType cardType;
    private boolean bankCalled;
    private boolean authorized;
    private long responseTime;
    private ProcessingMode processingMode;

}
