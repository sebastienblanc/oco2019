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

import org.sebjef.easystore.entity.CardType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.java.Log;

/**
 * Defines all checks required for a Card
 *
 * @author JF James
 */
@ApplicationScoped
@Log
public class CardValidator {

    @PersistenceContext(unitName = "merchant")
    private EntityManager em;

    public boolean checkExpiryDate(String expiryDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        Date expiry;
        try {
            expiry = simpleDateFormat.parse(expiryDate);
        } catch (ParseException ex) {
            log.log(Level.WARNING, "checkExpiryDate NOK, unparsable value {0}, MM/yy format expected", expiryDate);
            return false;
        }

        if (expiry.before(new Date())) {
            log.log(Level.WARNING, "checkExpiryDate NOK, expired date {0}", expiryDate);
            return false;
        }
        return true;

    }

    private static boolean checkLuhnKey(String cardNumber) {
        int[] ints = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }

        if (sum % 10 != 0) {
            log.log(Level.WARNING, "checkLuhnKey NOK {0}", cardNumber);
            return false;
        }

        return true;
    }

    public boolean isBlackListed(String cardNumber) {
        long count = em.createNamedQuery("CardRef.isBlackListed", Long.class).setParameter("cardNumber", cardNumber).getSingleResult();
        if (count != 0) {
            log.warning("cardNumber " + cardNumber + " is black listed");
            return true;
        }

        return false;
    }

    public boolean checkCardNumber(String cardNumber) {

        // Check format
        String card = cardNumber.replaceAll("[^0-9]+", ""); // remove all non-numerics
        if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
            return false;
        }

        // Check Luhn key
        return checkLuhnKey(card);
    }

    public CardType checkCardType(String cardNumber) {
        return CardType.detect(cardNumber);
    }

}
