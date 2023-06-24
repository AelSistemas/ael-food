package br.com.ael.food.configs.validador;


import br.com.ael.food.handlers.ErrorResponseValid;
import br.com.ael.food.handlers.ValidExceptionResponse;
import br.com.caelum.stella.validation.CPFValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Validador {

    private Boolean emailValidator(String email) {
        boolean valido = false;
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            valido = true;
        }
        return valido;
    }

    private void validaCPF(String cpf) throws Exception {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(cpf);
    }


    public void validacaoEmail( String cpf, String email) {
        List<ErrorResponseValid> errorResponseValids = new ArrayList<>();

        if (cpf != null) {
            try {
                this.validaCPF(cpf);
            } catch (Exception e) {
                errorResponseValids.add(new ErrorResponseValid(LocalDateTime.now(), e.getMessage()));
            }
        }

        if (email != null) {
            Boolean valido = this.emailValidator(email);

            if (valido == false) {
                errorResponseValids.add(new ErrorResponseValid(LocalDateTime.now(), "Email inválido"));
            }
        }

        throw new ValidExceptionResponse(errorResponseValids);
    }
}
