package org.hibernate.id;

import oracle.jdbc.internal.OraclePreparedStatement;
import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.insert.AbstractReturningDelegate;
import org.hibernate.id.insert.IdentifierGeneratingInsert;
import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Julia on 03.05.2017.
 */
public class Ora12IdentityGenerator extends AbstractPostInsertGenerator {
    @Override
    public InsertGeneratedIdentifierDelegate getInsertGeneratedIdentifierDelegate(
            PostInsertIdentityPersister persister, Dialect dialect, boolean isGetGeneratedKeysEnabled)
            throws HibernateException {
        return new Delegate(persister, dialect);
    }

    public static class Delegate extends AbstractReturningDelegate {

        private Dialect dialect;
        private String[] keyColumns;
        private int keyId;

        public Delegate(PostInsertIdentityPersister persister, Dialect dialect) {
            super(persister);
            this.dialect = dialect;
            this.keyColumns = getPersister().getRootTableKeyColumnNames();
            if (keyColumns.length > 1) {
                throw new HibernateException(
                        "trigger assigned identity generator cannot be used with multi-column keys");
            }
        }

        @Override
        public IdentifierGeneratingInsert prepareIdentifierGeneratingInsert() {
            return new SequenceIdentityGenerator.NoCommentsInsert(dialect);
        }

        @Override
        protected PreparedStatement prepare(String insertSQL, SessionImplementor session)
                throws SQLException {
            insertSQL = insertSQL + " returning "+keyColumns[0]+" into ?";
            OraclePreparedStatement os = (OraclePreparedStatement)session.connection().prepareStatement(insertSQL);
            keyId = insertSQL.split("\\?").length;
            os.registerReturnParameter(keyId, Types.DECIMAL);
            return os;
        }

        @Override
        protected Serializable executeAndExtract(PreparedStatement insert, SessionImplementor session)
                throws SQLException {

            OraclePreparedStatement os = (OraclePreparedStatement)insert;
            os.executeUpdate();

            ResultSet generatedKeys = os.getReturnResultSet();
            if (generatedKeys == null) {
                throw new HibernateException("Nullable Resultset");
            }
            try {
                return IdentifierGeneratorHelper.getGeneratedIdentity(
                        generatedKeys,
                        keyColumns[0],
                        getPersister().getIdentifierType());
            } finally {
                generatedKeys.close();
            }
        }
    }
}
