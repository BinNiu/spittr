package spitter;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spittr.config.RootConfig;
import spittr.domain.Spitter;
import spittr.domain.Spittle;
import spittr.repository.SpitterRepository;
import spittr.repository.SpittleRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class JdbcSpitterResponsitoryTest {
	@Autowired
	private SpitterRepository spitterResponitory;
	@Autowired
	private SpittleRepository spittleRepository;
	
	@Test
	public void testSpitter() {
		assertNotNull(spitterResponitory);
		System.out.println("count:"+spitterResponitory.count());
		spitterResponitory.save(new Spitter("ben", "789", "Ben", "Lee", "Ben Lee", "ben@gmail.com", false));
		System.out.println("findOne:"+spitterResponitory.findOne(2));
		System.out.println("findUserName:"+spitterResponitory.findByUsername("tom"));
		System.out.println("findAll");
		for(Spitter spitter : spitterResponitory.findAll()) {
			System.out.println(spitter);
		}
	
	}
	
	@Test
	public void testSpittle() {
		assertNotNull(spittleRepository);
		System.out.println("count:"+spittleRepository.count());
		spittleRepository.save(new Spittle("first spittle", new Date(), 1L));
		System.out.println("findOne:"+spittleRepository.findOne(3));
		
		for(Spittle spittle : spittleRepository.findRecent()) {
			System.out.println(spittle);
		}
		System.out.println("------------");
		for(Spittle spittle : spittleRepository.findRecent(3)) {
			System.out.println(spittle);
		}
		
		for(Spittle spittle : spittleRepository.findBySpitterId(1)) {
			System.out.println(spittle);
		}
	}
}
