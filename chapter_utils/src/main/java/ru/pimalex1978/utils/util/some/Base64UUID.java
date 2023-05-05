package ru.pimalex1978.utils.util.some;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created on 06.12.2017
 *
 * @author Maxim Avilov
 */
public class Base64UUID {

    //длина генерируемого кода
    public static final int BASE_64_ID_PART_LEN = 22;

    //повторить символ указанное количество раз
    public static final String BASE_64_ID_LIKE_ADDITIONAL = StringUtils.repeat('_', BASE_64_ID_PART_LEN);

    public static final int MAX_BASE_64_LEVELS = 32;

    public static final int MAX_BASE_64_ID_LENGTH = MAX_BASE_64_LEVELS * BASE_64_ID_PART_LEN;

    //генерируем родительский код
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(buffer.array());
    }

    //генерируем дочерний код, который добавляем к родительскому
    public static String generateUUID(String parentId) {
        if (parentId == null) {
            return generateUUID();
        } else if (parentId.length() + BASE_64_ID_PART_LEN > MAX_BASE_64_ID_LENGTH) {
            throw new IllegalStateException("Parent Base64UUID to large!");
        } else if (parentId.length() % BASE_64_ID_PART_LEN != 0) {
            throw new IllegalArgumentException("Invalid parent Base64UUID!");
        }
        return parentId + generateUUID();
    }

    public static int getIdLavelNumFromOne(String base64UUID) {
        if (base64UUID == null || base64UUID.length() < BASE_64_ID_PART_LEN || base64UUID.length() % BASE_64_ID_PART_LEN != 0) {
            throw new IllegalArgumentException("Invalid Base64UUID!");
        }
        return base64UUID.length() / BASE_64_ID_PART_LEN;
    }

    //получить родительский код из имеющегося целого
    public static String getParentId(String base64UUID) {
        if (base64UUID == null || base64UUID.length() < BASE_64_ID_PART_LEN || base64UUID.length() % BASE_64_ID_PART_LEN != 0) {
            throw new IllegalArgumentException("Invalid Base64UUID!");
        }
        return base64UUID.substring(0, base64UUID.length() - BASE_64_ID_PART_LEN);
    }

    public static boolean isParent(String forChild, String candidateToParent) {
        return candidateToParent != null && forChild != null && !candidateToParent.isEmpty() && forChild.startsWith(candidateToParent);
    }
}
