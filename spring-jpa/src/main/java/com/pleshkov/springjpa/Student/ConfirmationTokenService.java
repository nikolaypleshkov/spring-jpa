package com.pleshkov.springjpa.Student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmTokenRepository;

    void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmTokenRepository.save(confirmationToken);
    }
    void deleteConfirmationToken(Long id){
        confirmTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token){
        return confirmTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }


}
