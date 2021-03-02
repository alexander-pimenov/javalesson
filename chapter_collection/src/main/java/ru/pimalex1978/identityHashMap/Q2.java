package ru.pimalex1978.identityHashMap;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * В чем разница между HashMap и IdentityHashMap? Для чего нужна IdentityHashMap?
 * IdentityHashMap - это структура данных, так же реализующая интерфейс Map и использующая при
 * сравнении ключей (значений) сравнение ссылок, а не вызов метода equals(). Другими словами,
 * в IdentityHashMap два ключа k1 и k2 будут считаться равными, если они указывают на один объект,
 * т.е. выполняется условие k1 == k2.
 *
 * IdentityHashMap не использует метод hashCode(), вместо которого применяется метод System.identityHashCode(),
 * по этой причине IdentityHashMap по сравнению с HashMap имеет более высокую производительность,
 * особенно если последний хранит объекты с дорогостоящими методами equals() и hashCode().
 *
 * Одним из основных требований к использованию HashMap является неизменяемость ключа,
 * а, т.к. IdentityHashMap не использует методы equals() и hashCode(), то это правило на
 * него не распространяется.
 *
 * IdentityHashMap может применяться для реализации сериализации/клонирования. При выполнении
 * подобных алгоритмов программе необходимо обслуживать хэш-таблицу со всеми ссылками на объекты,
 * которые уже были обработаны. Такая структура не должна рассматривать уникальные объекты как равные,
 * даже если метод equals() возвращает true.
 */
public class Q2 {
    public static void main(String[] args) {
        Q2 q = new Q2();
        q.testHashMapAndIdentityHashMap();
    }

    private void testHashMapAndIdentityHashMap() {
        CreditCard visa = new CreditCard("VISA", "04/12/2019");

        Map<CreditCard, String> cardToExpiry = new HashMap<>();
        Map<CreditCard, String> cardToExpiryIdenity = new IdentityHashMap<>();

        System.out.println("adding to HM");
        // inserting objects to HashMap
        cardToExpiry.put(visa, visa.getExpiryDate());

        // inserting objects to IdentityHashMap
        cardToExpiryIdenity.put(visa, visa.getExpiryDate());
        System.out.println("adding to IHM");

        System.out.println("before modifying keys");
        String result = cardToExpiry.get(visa) != null ? "Yes" : "No";
        System.out.println("Does VISA card exists in HashMap? " + result);

        result = cardToExpiryIdenity.get(visa) != null ? "Yes" : "No";
        System.out.println("Does VISA card exists in IdenityHashMap? " + result);

        // modifying value object
        visa.setExpiryDate("02/11/2030");

        System.out.println("after modifying keys");
        result = cardToExpiry.get(visa) != null ? "Yes" : "No";
        System.out.println("Does VISA card exists in HashMap? " + result);

        result = cardToExpiryIdenity.get(visa) != null ? "Yes" : "No";
        System.out.println("Does VISA card exists in IdenityHashMap? " + result);

        System.out.println("cardToExpiry.containsKey");
        System.out.println(cardToExpiry.containsKey(visa));
        System.out.println("cardToExpiryIdenity.containsKey");
        System.out.println(cardToExpiryIdenity.containsKey(visa));
    }

}

class CreditCard {
    private String issuer;
    private String expiryDate;

    CreditCard(String issuer, String expiryDate) {
        this.issuer = issuer;
        this.expiryDate = expiryDate;
    }

    public String getIssuer() {
        return issuer;
    }

    String getExpiryDate() {
        return expiryDate;
    }

    void setExpiryDate(String expiry) {
        this.expiryDate = expiry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
        System.out.println("hashCode = " + result);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals !!! ");
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreditCard other = (CreditCard) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null)
                return false;
        } else if (!expiryDate.equals(other.expiryDate))
            return false;
        if (issuer == null) {
            if (other.issuer != null)
                return false;
        } else if (!issuer.equals(other.issuer))
            return false;
        return true;
    }
}

