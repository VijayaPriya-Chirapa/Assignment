//package com.Application1.in.springBatch;
//
//import java.io.FileWriter;
//
//import java.io.IOException;
//
//import java.util.List;
// 
//import org.springframework.batch.core.StepContribution;
//
//import org.springframework.batch.core.scope.context.ChunkContext;
//
//import org.springframework.batch.core.step.tasklet.Tasklet;
//
//import org.springframework.batch.repeat.RepeatStatus;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.Application1.in.EntityVO.UserVO;
// 
// 
//@Configuration
//
//public class UserTasklet implements Tasklet {
// 
//    // Inject JdbcTemplate to access the database
//
//    @Autowired
//
//    private JdbcTemplate jdbcTemplate;
// 
//    @Override
//
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//        // Query the database to fetch data
//
//        String sql = "SELECT * FROM user_table"; // Replace with your table and query
//
//        List<UserVO> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
//
//        	UserVO user = new UserVO();
//
//            user.setId(rs.getLong("id"));
//
//            user.setName(rs.getString("name"));
//
//            user.setEmail(rs.getString("email"));
//
//            user.setPassword(rs.getString("password"));
//
//            return user;
//
//        });
// 
//        // Write data to CSV
//
//        try (FileWriter fileWriter = new FileWriter("users.csv")) {
//
//            // Write CSV header
//
//            fileWriter.append("id, name, email, password\n");
// 
//            // Write data rows
//
//            for (UserVO user : users) {
//
//                fileWriter.append(user.getId() + ", ");
//
//                fileWriter.append(user.getName() + ", ");
//
//                fileWriter.append(user.getEmail() + ", ");
//
//                fileWriter.append(user.getPassword() + "\n");
//
//            }
//
//        } catch (IOException e) {
//
//            throw new RuntimeException("Error writing to CSV", e);
//
//        }
// 
//        return RepeatStatus.FINISHED; // Indicate that the tasklet has finished
//
//    }
//
//}
//
//
// 
// 
