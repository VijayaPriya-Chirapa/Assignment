package com.Application1.in.springBatch;
import java.util.Collections;
 
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import com.Application1.in.EntityVO.UserVO;
import com.Application1.in.Repository.UserRepository;

 
@Configuration
public class BatchConfig {
	
	private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;
 
    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }
 
    // 1. Reader - RepositoryItemReader to fetch data from the database
    @Bean
    public RepositoryItemReader<UserVO> reader() {
        RepositoryItemReader<UserVO> reader = new RepositoryItemReader<>();
        reader.setRepository(userRepository);
        reader.setMethodName("findAll");
        reader.setPageSize(10);
        reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        return reader;
    }
 
    // 2. Processor - PassThroughItemProcessor (no transformation in this case)
    @Bean
    public PassThroughItemProcessor<UserVO> processor() {
        return new PassThroughItemProcessor<>();
    }
 
    // 3. Writer - FlatFileItemWriter to write data to a CSV file
    @Bean
    public FlatFileItemWriter<UserVO> writer() {
        return new FlatFileItemWriterBuilder<UserVO>()
                .name("csvWriter")
                .resource(new FileSystemResource("outputfile.csv"))
                .delimited()
                .delimiter(",")
                .names("id", "name", "email","password")
                .headerCallback(writer -> writer.write("ID,Name,email,password"))
                .build();
    }
 
    // 4. Step - Defines a chunk-oriented step
    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .<UserVO, UserVO>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
 
    // 5. Job - Assembles the step into a Job
    @Bean
    public Job job() {
        return new org.springframework.batch.core.job.builder.JobBuilder("BatchConfig", jobRepository)
                .start(step())
                .build();
    }
}