package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DeveloperData implements ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CarRepository carRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("I was called");

        Member m1 = new Member("member1", "memb1@a.dk", "1234", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Member m2 = new Member("member2", "aaa@dd.dk", "4321", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");


        Car car1 = new Car("Volvo", "CX90", 895, 695);
        Car car2 = new Car("BMW", "i330", 795, 645);
        Car car3 = new Car("VW", "ID4", 695, 595);


        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

        List<String> colours1 = new ArrayList<>();
        colours1.add("blue");
        colours1.add("red");

        List<String> colours2 = new ArrayList<>();
        colours2.add("silver");
        colours2.add("green");

        m1.setFavoriteCarColors(colours1);
        m2.setFavoriteCarColors(colours2);

        Map<String, String> phonenumbers = new HashMap<>();
        phonenumbers.put("mobile","12345");
        phonenumbers.put("work", "45678");
        m1.setPhones(phonenumbers);
        m2.setPhones(phonenumbers);

        memberRepository.save(m1);
        memberRepository.save(m2);
    }


}
