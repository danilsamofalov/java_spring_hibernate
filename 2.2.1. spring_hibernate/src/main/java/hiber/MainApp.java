package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User vasya = new User("Vasya", "Pupkin", "vasyapupkin@gmail.com");
        User zhora = new User("Zhora", "Levin", "zhoralevin@gmail.com");
        User petya = new User("Petya", "Andronov", "petyaandronov@gmail.com");

        Car volvo = new Car("Volvo", 3);
        Car mercedes = new Car("Mercedes", 6);
        Car bmw = new Car("BMW", 5);

        userService.add(vasya.setUserCar(mercedes).setCarUser(vasya));
        userService.add(zhora.setUserCar(bmw).setCarUser(zhora));
        userService.add(petya.setUserCar(volvo).setCarUser(petya));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user + " " + user.getUserCar());
        }

        System.out.println(userService.getUserByCar("Volvo", 3));
        System.out.println(userService.getUserByCar("Mercedes", 6));

        context.close();
    }
}
