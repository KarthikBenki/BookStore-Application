package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.entity.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.OtpGenerator;
import com.bridgelabz.bookstoreapp.util.TokenGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService {

    @Autowired
    UserData userData;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OtpGenerator otpGenerator;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private TokenGenerator jwtToken;

    Long otp;

    /**
     *
     * @param userDTO
     * @return registers the users in the database
     */
    @Override
    public ResponseDTO registerUserInBookStore(UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        UserData user = userRegistrationRepository.findUserDataByEmail(userDTO.getEmail());
        if (user == null) {//check for user exists
            //maps the userdto with userdata class
            userData = modelMapper.map(userDTO, UserData.class);
            userData.setCreatedDate(LocalDate.now());
            //encrypting the password using password encoder
            String epassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            userData.setPassword(epassword);
            System.out.println("password is " + epassword);
            userData = userRegistrationRepository.save(userData);
            System.out.println(userData);
            otp = generateOtpAndSendEmail(userData);
            responseDTO.setMessage("User Created successfully");
            responseDTO.setData(userData);
        } else {
            responseDTO.setMessage("user not created");
            responseDTO.setData("user with " + userDTO.getEmail() + " is already exists");
        }
        return responseDTO;
    }

    /**
     *
     * @param userData
     * @return creates the otp and sends the email
     */
    private Long generateOtpAndSendEmail(UserData userData) {
        long generatedOtp = otpGenerator.generateOTP();
        String requestUrl = "http://localhost:8080/bookstoreApi/verify/email/" + generatedOtp;
        System.out.println("the generated otp is " + generatedOtp);
        try {
            emailSenderService.sendEmail(
                    userData.getEmail(),
                    "Your Registration is successful",
                    requestUrl + "\n your generated otp is "
                            + generatedOtp +
                            " click on the link above to verify the user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedOtp;
    }

    /**
     *
     * @param userLoginDTO
     * @return logins the user and gives access to bookstore
     */
    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserData> userList = userRegistrationRepository.findAll();
        UserData userDataByEmail = userRegistrationRepository.findUserDataByEmail(userLoginDTO.getEmail());
        if (userList.contains(userDataByEmail)) {
            String password = userDataByEmail.getPassword();
            //checking for password encryption match with raw passowrd
            if (bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), password)) {
                if (!userDataByEmail.getIsVerified()) {
                    otp = generateOtpAndSendEmail(userDataByEmail);
                    responseDTO.setMessage("Sorry! login is unsuccessful");
                    responseDTO.setData("please go to your email and verify");
                    return responseDTO;
                }
                String tokenString = jwtToken.generateLoginToken(userDataByEmail);
                responseDTO.setMessage("login SuccessFul");
                responseDTO.setData(tokenString);
                return responseDTO;
            } else {
                responseDTO.setMessage("Sorry! login is unsuccessful");
                responseDTO.setData("Wrong password");
                return responseDTO;
            }
        }
        return new ResponseDTO("User not found!", "Wrong email");
    }

    /**
     *
     * @param mailOtp
     * @return verifies the otp using mail
     */
    public ResponseDTO verifyEmailUsingOtp(Long mailOtp) {
        if (mailOtp.equals(otp) && userData.getIsVerified().equals(false)) {
            userData.setIsVerified(true);//setting verification to true after verification
            //update the userData with is verified value
            userRegistrationRepository.save(userData);
            return new ResponseDTO("otp verified", userData);
        } else if (userData.getIsVerified() && mailOtp.equals(otp)) {
            return new ResponseDTO("otp already verified no need to verify again", userData);
        }
        return new ResponseDTO("Invalid otp", "please enter correct otp");
    }

    /**
     * @Purpose to delete the user using his id
     * @param id
     * @return
     */
    @Override
    public String deleteUserById(long id) {
        userData = findUserById(id);
        userRegistrationRepository.delete(userData);
        return userData.getEmail();
    }

    /**
     *
     * @param id
     * @return returns the user details if found using id
     */
    @Override
    public UserData findUserById(long id) {
        return userRegistrationRepository.findById(id).get();
    }

    /**
     *
     * @return returns all the users in the database
     */
    @Override
    public List<UserData> findAllUsers() {
        return userRegistrationRepository.findAll();
    }

    /**
     *
     * @param id
     * @param userDTO
     * @return returns data and status updated
     */
    @Override
    public UserData updateUserbyId(Long id, UserDTO userDTO) {
        userData = userRegistrationRepository.findById(id).get();
        userData.updateUserData(userDTO);
        return userRegistrationRepository.save(userData);
    }


}
