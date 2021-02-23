package nestedclasses.nonStaticExample;

import java.util.Date;

/*для работы со счетами нужно делать некоторые операции*/
public interface AccountOperation {
    Date getDate(); //время проведения операции
    int amount(); //  количество денег которое в рамках єтой операции было либо добавлено либо взято

}
