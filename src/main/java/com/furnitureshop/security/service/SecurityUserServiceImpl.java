package com.furnitureshop.security.service;


//import com.techshop.security.repository.PasswordResetTokenRepository;

import com.furnitureshop.security.dto.ForgotPasswordDto;
import com.furnitureshop.security.repository.PasswordResetTokenRepository;
import com.furnitureshop.security.repository.VerificationTokenRepository;
import com.furnitureshop.user.entity.PasswordResetToken;
import com.furnitureshop.user.entity.User;
import com.furnitureshop.user.entity.VerificationToken;
import com.furnitureshop.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService {
    private VerificationTokenRepository verificationTokenRepository;
    private PasswordResetTokenRepository passwordResetTokenRepository;
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public SecurityUserServiceImpl(PasswordEncoder encoder, VerificationTokenRepository verificationTokenRepository, UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository){
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.encoder = encoder;
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken myToken = new VerificationToken();
        myToken.setToken(token);
        myToken.setUser(user);
     ;
        return  verificationTokenRepository.save(myToken);
    }

    @Override
    public Boolean verifyMailToken(String VerificationToken) {
        Optional<VerificationToken> verification = verificationTokenRepository.findByToken(VerificationToken);
        if(!verification.isPresent())
            throw new IllegalStateException("Invalid Token");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(verification.get().getExpiryDate().before(calendar.getTime()))
            throw new IllegalStateException("Expired token");

        User user = verification.get().getUser();
        user.setActiveFlag("Y");
        userRepository.save(user);

        return true;
    }

    @Override
    public PasswordResetToken createPasswordResetToken(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isPresent())
            throw new IllegalStateException("This email haven't been registered in our system");

        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setUser(user.get());

        return  passwordResetTokenRepository.save(myToken);
    }

    @Override
    public void verifyPasswordResetToken(ForgotPasswordDto dto) {
        Optional<PasswordResetToken> verification = passwordResetTokenRepository.findByToken(dto.getToken());
        if(!verification.isPresent())
            throw new IllegalStateException("Invalid Token");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(verification.get().getExpiryDate().before(calendar.getTime()))
            throw new IllegalStateException("Expired token");

        User user = verification.get().getUser();
        user.setPassword(encoder.encode(dto.getPassword()));
        userRepository.save(user);

    }

    @Transactional
    @Override
    public void removeReferenceUser(String username) {
        User user = userRepository.findByUsername(username).get();

        verificationTokenRepository.deleteByUserId(user.getUserId());
        passwordResetTokenRepository.deleteByUserId(user.getUserId());
        userRepository.deleteByUsername(username);
    }


//    private final PasswordResetTokenRepository passwordTokenRepository;

//    @Autowired
//    public SecurityUserServiceImpl(PasswordResetTokenRepository repository) {
//        this.passwordTokenRepository = repository;
//    }

//    @Override
//    public String validatePasswordResetToken(String username, String token) {
//        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
//        if ((passToken == null) || (passToken.getUser().getUsername()!= username)) {
//            return "invalid token";
//        }
//
//        final Calendar cal = Calendar.getInstance();
//        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            return "expired";
//        }
//
//        return "valid token";
//
//    }
//
//    @Override
//    public String getForgotPasswordToken(String email) {
//        return null;
//    }

//    @Override
//    public String getForgotPasswordToken(String email) {
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//    }
}
