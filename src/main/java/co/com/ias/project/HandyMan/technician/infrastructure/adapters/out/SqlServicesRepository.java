package co.com.ias.project.HandyMan.technician.infrastructure.adapters.out;

import co.com.ias.project.HandyMan.technician.application.domain.Calculation;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceId;
import co.com.ias.project.HandyMan.technician.application.domain.ServiceReport;
import co.com.ias.project.HandyMan.technician.application.domain.TechnicianId;
import co.com.ias.project.HandyMan.technician.application.ports.out.ServiceReportsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository("sql")
public class SqlServicesRepository implements ServiceReportsRepository {
    private final JdbcTemplate jdbcTemplate;

    public SqlServicesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ServiceReport> serviceReportRowMapper = (rs, rowNum) -> fromResultSet(rs);

    @Override
    public Optional<ServiceReport> getServiceReportByServiceId(ServiceId serviceId) {
        final String sql = "SELECT * FROM REPORTS WHERE SERVICE_ID = ?";
        PreparedStatementSetter preparedStatementSetter = ps -> ps.setString(1, serviceId.getValue());
        final ResultSetExtractor<Optional<ServiceReport>> resultSetExtractor = rs -> {
            if (rs.next()) {
                final ServiceReport serviceReport = fromResultSet(rs);
                return Optional.of(serviceReport);
            } else {
                return Optional.empty();
            }
        };

        return jdbcTemplate.query(sql, preparedStatementSetter, resultSetExtractor);
    }

    @Override
    public void storeServiceReport(ServiceReport serviceReport) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO REPORTS (TECHNICIAN_ID, SERVICE_ID, START_DATE_TIME, END_DATE_TIME) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, serviceReport.getTechnicianId().getValue());
            preparedStatement.setString(2, serviceReport.getServiceId().getValue());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(serviceReport.getStartDateTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(serviceReport.getEndDateTime()));

            return preparedStatement;
        });
    }


    @Override
    public Collection<ServiceReport> listServiceReports(TechnicianId technicianId, int limit, int skip) {
        final String sql ="SELECT * FROM REPORTS WHERE TECHNICIAN_ID= ? LIMIT ? OFFSET ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, technicianId.getValue());
            ps.setString(2, String.valueOf(limit));
            ps.setString(3, String.valueOf(skip));
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, serviceReportRowMapper);
    }

    @Override
    public Object[] calculateHours(TechnicianId technicianId, int numberOfWeek) {
        final String sql ="SELECT * FROM REPORTS WHERE TECHNICIAN_ID= ?";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, technicianId.getValue());
        };
        Collection<ServiceReport> reports = jdbcTemplate.query(sql, preparedStatementSetter, serviceReportRowMapper);

        Object[] workedHours = Calculation.calculation(reports, numberOfWeek);

        return workedHours;
    }


    private static ServiceReport fromResultSet(ResultSet rs) throws SQLException {
        return ServiceReport.from(
                TechnicianId.of(rs.getString("TECHNICIAN_ID")),
                ServiceId.of(rs.getString("SERVICE_ID")),
                rs.getTimestamp("START_DATE_TIME").toLocalDateTime(),
                rs.getTimestamp("END_DATE_TIME").toLocalDateTime()
        );
    }


}

