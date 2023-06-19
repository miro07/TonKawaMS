package auth.services.authservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthFactory {
        @Autowired
        SellerAuthServiceImpl sellerAuthService;

        @Autowired
        CrmAuthService crmAuthService;

        public AuthService execute(String application) {

            switch (application) {
                case "ERP":
                    return sellerAuthService;
                default:
                    return crmAuthService;
            }
        }
}
