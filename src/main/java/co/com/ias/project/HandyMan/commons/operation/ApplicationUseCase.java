package co.com.ias.project.HandyMan.commons.operation;

public interface ApplicationUseCase<IN extends ApplicationRequest, OUT extends ApplicationResponse> {
    OUT execute(IN request);
}
