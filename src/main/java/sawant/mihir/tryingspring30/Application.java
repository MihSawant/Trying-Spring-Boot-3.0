package sawant.mihir.tryingspring30;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
@RestController
class MyController{

	@GetMapping("/")
	public String helloMessage(){
		return "Hello World !";
	}

	@GetMapping("/percentage/{marks}")
	public String getPercentage(@PathVariable int marks){
		double percentage = 0.0;
		if(marks > 50) {
			throw new IllegalArgumentException("Marks cannot be greater than 50");
		}
		percentage = (double) marks / 50;
		return String.format("Percentage: %.2f",percentage * 100);
	}
}

@ControllerAdvice
class MarksInputExceptionHandler{

	@ExceptionHandler(IllegalArgumentException.class)
	private ProblemDetail handleMarksGreaterThan50(){
		return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
				"Total marks must be less than 50");
	}

}
