package ru.pimalex1978.utils.util.some;

import lombok.experimental.UtilityClass;

@SuppressWarnings("all")
@UtilityClass
public class SqlQueries {

    public static final String GET_NEXT_SERVICE_FEMIDA_ID = "SELECT \"FBIntegration\".getNextServiceFemidaId()";
    public static final String GET_CERTAIN_AMOUNT_OF_RECORDS_WITH_STATE_AND_OBJECT_TYPE
            = "SELECT DISTINCT ON(integration_id) transaction_id,transactions.integration_id,femida_id,beamery_id,"
            + "state,object_type,transaction_type,updated_datetime,effective_date_time,dto,beamery_hash_id "
            + "FROM \"FBIntegration\".transactions "
            + "JOIN (SELECT integration_id,femida_id,beamery_id,beamery_hash_id,state,object_type,updated_datetime "
            + "FROM \"FBIntegration\".object_states WHERE state = ? AND object_type = ? "
            + "ORDER BY updated_datetime ASC LIMIT ? FOR UPDATE SKIP LOCKED) AS os "
            + "ON os.integration_id = transactions.integration_id "
            + "ORDER BY integration_id, effective_date_time DESC, transaction_id DESC";
    public static final String GET_CERTAIN_AMOUNT_OF_SENT_PINNED_NOTES
            = "SELECT DISTINCT ON(integration_id) transaction_id,transactions.integration_id,femida_id,beamery_id,"
            + "state,object_type,transaction_type,updated_datetime,effective_date_time,dto,beamery_hash_id "
            + "FROM \"FBIntegration\".transactions "
            + "JOIN (SELECT integration_id,femida_id,beamery_id,beamery_hash_id,state,object_type,updated_datetime "
            + "FROM \"FBIntegration\".object_states WHERE state = ? AND object_type = ? AND femida_id < 0 "
            + "ORDER BY integration_id ASC LIMIT ? FOR UPDATE SKIP LOCKED) AS os "
            + "ON os.integration_id = transactions.integration_id "
            + "ORDER BY integration_id, effective_date_time DESC, transaction_id DESC";
    public static final String SET_BEAMERY_ID_STATE_UPDATED_DATE_TIME_BEAMERY_HASH_ID_FOR_FEMIDA_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states SET beamery_id = ?, state = ?, updated_datetime = ?, beamery_hash_id = ? "
            + "WHERE femida_id = ? and object_type = ?";
    public static final String SET_BEAMERY_ID_STATE_UPDATED_DATE_TIME_FOR_FEMIDA_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states SET beamery_id = ?, state = ?, updated_datetime = ? "
            + "WHERE femida_id = ? and object_type = ?";
    public static final String SET_STATE_FOR_FEMIDA_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states SET state = ? WHERE femida_id = ? AND object_type = ?";
    public static final String SET_STATE_AND_UPDATED_DATETIME_FOR_FEMIDA_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states "
            + "SET state = ?, updated_datetime = ?, retry_count = 0, next_retry_datetime = now() WHERE femida_id = ? AND object_type = ?";
    public static final String SET_STATE_AND_UPDATED_DATETIME_FOR_FEMIDA_ID_BEAMERY_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states SET state = ?, updated_datetime = ? "
            + "WHERE femida_id = ? AND beamery_id = ? AND object_type = ?";
    public static final String SET_STATE_FOR_BEAMERY_ID_OBJECT_TYPE_UPDATED_DATETIME_EQUALS
            = "UPDATE \"FBIntegration\".object_states SET state = ? "
            + "WHERE beamery_id = ? AND object_type = ? AND updated_datetime = ?";
    public static final String SET_STATE_FOR_INTEGRATION_ID_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states SET state = ? "
            + "WHERE integration_id = ? AND object_type = ?";
    public static final String GET_ALL_SENT_TO_BEAMERY_RECORDS_BEAMERY_IDS_WITH_TYPE
            = "SELECT beamery_id FROM \"FBIntegration\".object_states "
            + "WHERE state = ? AND object_type = ? AND femida_id >= 0 FOR UPDATE SKIP LOCKED";
    public static final String SELECT_MIN_UPDATED_DATETIME_WITH_SENT_TO_BEAMERY_STATE_AND_OBJECT_TYPE
            = "SELECT min(updated_datetime) FROM \"FBIntegration\".object_states "
            + "WHERE state = ? AND object_type = ? AND femida_id >= 0";
    public static final String MARK_LAGGING_RECORDS_OF_CONCRETE_TYPE_FOR_RETRY
            = "UPDATE \"FBIntegration\".object_states SET state = :targetState "
            + "WHERE state = :sourceState AND object_type = :objectType AND femida_id >= 0 AND integration_id IN (:integrationIds)";
    public static final String GET_BEAMERY_ID_BY_FEMIDA_ID_AND_OBJECT_TYPE
            = "SELECT DISTINCT ON (beamery_id) beamery_id "
            + "FROM \"FBIntegration\".object_states JOIN \"FBIntegration\".transactions t USING (integration_id) "
            + "WHERE ((transaction_type = 'POST' "
            + "AND state NOT IN ('PENDING_FOR_BEAMERY', 'SENT_TO_BEAMERY', 'RETRY_TO_BEAMERY', 'BAD_JSON', 'BAD_BEAMERY_RESPONSE')) "
            + "OR (transaction_type = 'PATCH')) AND beamery_id NOTNULL "
            + "AND femida_id = ? AND object_type = ? ORDER BY beamery_id ASC, effective_date_time DESC, transaction_id DESC";
    public static final String SAVE_TRANSACTION_RECORD
            = "INSERT INTO \"FBIntegration\".transactions (integration_id,transaction_type,effective_date_time,dto,dto_md5,direction) "
            + "VALUES (?,?,?,?::jsonb,MD5(?),'TO_BEAMERY')";
    public static final String SET_UPDATED_DATETIME_FOR_FEMIDA_ID_AND_OBJECT_TYPE
            = "UPDATE \"FBIntegration\".object_states "
            + "SET updated_datetime = ?, retry_count = 0, next_retry_datetime = now() WHERE femida_id = ? AND object_type = ?";
    public static final String GET_LATEST_TRANSACTION_LOG_RECORD_BY_FEMIDA_ID_AND_OBJECT_TYPE
            = "SELECT integration_id,femida_id,beamery_id,state,object_type,"
            + "transaction_type,updated_datetime,effective_date_time,dto,beamery_hash_id "
            + "FROM \"FBIntegration\".object_states JOIN \"FBIntegration\".transactions USING(integration_id) "
            + "WHERE femida_id = ? AND object_type = ? "
            + "ORDER BY effective_date_time DESC, transaction_id DESC LIMIT 1";
    public static final String GET_LATEST_TRANSACTION_LOG_RECORD_BY_BEAMERY_ID_AND_OBJECT_TYPE
            = "SELECT integration_id,femida_id,beamery_id,state,object_type,"
            + "transaction_type,updated_datetime,effective_date_time,dto,beamery_hash_id "
            + "FROM \"FBIntegration\".object_states JOIN \"FBIntegration\".transactions USING(integration_id) "
            + "WHERE beamery_id = ? AND object_type = ? ORDER BY effective_date_time DESC, transaction_id DESC LIMIT 1";
    public static final String INSERT_RECORD_INTO_OBJECT_STATES
            = "INSERT INTO \"FBIntegration\".object_states (femida_id,state,object_type,updated_datetime) "
            + "VALUES (?,?,?,?)";
    public static final String INSERT_RECORD_INTO_TRANSACTIONS
            = "INSERT INTO \"FBIntegration\".transactions (integration_id,transaction_type,effective_date_time,dto,dto_md5,direction) "
            + "SELECT os.integration_id,(CASE WHEN os.beamery_id ISNULL THEN 'POST' ELSE 'PATCH' END),?,?::jsonb,MD5(?),'TO_BEAMERY' "
            + "FROM (SELECT integration_id, beamery_id FROM \"FBIntegration\".object_states o "
            + "WHERE o.femida_id = ? AND o.object_type = ?) AS os";
    public static final String SET_FEMIDA_ID_BY_INTEGRATION_ID
            = "UPDATE \"FBIntegration\".object_states SET femida_id = ? WHERE femida_id ISNULL AND integration_id = ?";
    public static final String ARCHIVE_OBJECT_BY_FEMIDA_ID_AND_OBJECT_TYPE
            = "WITH ins AS ("
            + "INSERT INTO \"FBIntegration\".archived_objects (integration_id,actual_femida_id,femida_id,beamery_id,state,object_type,"
            + "updated_datetime,beamery_hash_id) "
            + "SELECT integration_id,? AS actual_femida_id,femida_id,beamery_id,state,object_type,updated_datetime,beamery_hash_id "
            + "FROM \"FBIntegration\".object_states WHERE femida_id = ? AND object_type = ? LIMIT 1) "
            + "DELETE FROM \"FBIntegration\".object_states WHERE femida_id = ? AND object_type = ?";
    public static final String SELECT_ROW_COUNT_FROM_CANDIDATE_TO_NOTES_MAPPING_BY_CANDIDATE_ID_AND_TYPE
            = "SELECT count(*) FROM \"FBIntegration\".candidate_to_notes_mapping "
            + "WHERE candidate_id = (SELECT integration_id FROM \"FBIntegration\".object_states "
            + "WHERE femida_id = ? AND object_type = ? AND note_type = ? LIMIT 1)";
    public static final String INSERT_INTO_CANDIDATE_TO_NOTES_MAPPING
            = "INSERT INTO \"FBIntegration\".candidate_to_notes_mapping (candidate_id,note_id,note_type) "
            + "VALUES ((SELECT integration_id FROM \"FBIntegration\".object_states WHERE femida_id = ? AND object_type = ? LIMIT 1),"
            + "(SELECT integration_id FROM \"FBIntegration\".object_states WHERE femida_id = ? AND object_type = ? LIMIT 1), ?)";
    public static final String GET_NOTE_FEMIDA_ID_BY_CANDIDATE_ID_AND_TYPE_BY_CANDIDATE_FEMIDA_ID
            = "SELECT femida_id FROM \"FBIntegration\".object_states WHERE integration_id = "
            + "(SELECT note_id FROM \"FBIntegration\".candidate_to_notes_mapping WHERE candidate_id = "
            + "(SELECT integration_id FROM \"FBIntegration\".object_states WHERE femida_id = ? "
            + "AND object_type = 'com.epam.beamery.model.Candidate' LIMIT 1) "
            + "AND note_type = ? LIMIT 1)";
    public static final String UPDATE_RETRY_COUNT_AND_DATETIME_BY_INTEGRATION_ID
            = "UPDATE \"FBIntegration\".object_states "
            + "SET next_retry_datetime = next_retry_datetime + interval '1 minute' * power(2, retry_count), "
            + "retry_count = retry_count + 1 WHERE integration_id = ?";
    public static final String GET_RETRY_RECORDS_WITH_OBJECT_TYPE_AND_LIMIT
            = "SELECT DISTINCT ON (integration_id) transaction_id,transactions.integration_id,femida_id,beamery_id,"
            + "state,object_type,transaction_type, updated_datetime,effective_date_time,dto,beamery_hash_id "
            + "FROM \"FBIntegration\".transactions "
            + "JOIN (SELECT integration_id,femida_id,beamery_id,state,object_type,updated_datetime,beamery_hash_id "
            + "FROM \"FBIntegration\".object_states WHERE state = 'RETRY_TO_BEAMERY' AND next_retry_datetime < now() "
            + "AND object_type = ? ORDER BY retry_count ASC, next_retry_datetime ASC LIMIT ? FOR UPDATE SKIP LOCKED) as t "
            + "ON t.integration_id = transactions.integration_id "
            + "ORDER BY integration_id, effective_date_time DESC, transaction_id DESC";
}
