package top.bitplanet.devops.lowcode.dto.query;

import lombok.Data;

@Data
public class DataSourceInfoQuery {

    private String jdbcUrl;

    private String username;

    private String password;


}
