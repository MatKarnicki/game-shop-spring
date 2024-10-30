package lab3.zad2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import lab3.zad2.service.RoleService;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class Zad2Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Zad2Application.class, args);
		RoleService roleService = context.getBean(RoleService.class);
		roleService.printAllRoles();
	}
}
