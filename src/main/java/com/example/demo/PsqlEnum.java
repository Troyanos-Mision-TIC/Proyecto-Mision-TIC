package com.example.demo;

import org.hibernate.type.EnumType;
import java.sql.SQLException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import java.sql.PreparedStatement;
import java.sql.Types;

public class PsqlEnum extends EnumType {
    @Override
    public void nullSafeSet(final PreparedStatement st, final Object value, final int index,
            final SharedSessionContractImplementor session) throws SQLException {
        if(value == null) {
            st.setNull( index, Types.OTHER );
        }
        else {
            st.setObject(
                    index,
                    value.toString(),
                    Types.OTHER
            );
        }
    }
}
