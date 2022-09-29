package com.example.demo.controllers;

import com.example.demo.Role;
import com.example.demo.model.Employee;
import com.example.demo.model.Enterprise;
import com.example.demo.model.Profile;
import com.example.demo.model.Transaction;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EnterpriseService;
import com.example.demo.services.ProfileService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class FrontController {

    private EnterpriseService enterpriseService;
    private ProfileService profileService;
    private EmployeeService employeeService;

    public FrontController(EnterpriseService enterServ, ProfileService proServ, EmployeeService EmpServ) {
        enterpriseService = enterServ;
        profileService = proServ;
        employeeService = EmpServ;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());
            model.addAttribute("profile", profile);
        }
        return "index";
    }

    @GetMapping("/enterprises-list")
    public String enterprises(Enterprise enterprise, Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());

            model.addAttribute("profile", profile);

            Employee profileEmployee = employeeService.findByEmail(profile.getEmail());

            model.addAttribute("profileEmployee", profileEmployee);
            model.addAttribute("isUserAdmin", profile.getRole() == Role.ADMIN);
        }
        List<Enterprise> enterprises = enterpriseService.findAll();
        model.addAttribute("empresas", enterprises);
        return "enterprises";
    }

    @GetMapping("/enterprise/{id}/employees")
    public String enterpriseEmployees(@PathVariable long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());
            model.addAttribute("profile", profile);
            model.addAttribute("isUserAdmin", profile.getRole() == Role.ADMIN);
        }

        Optional<Enterprise> optEnterprise = enterpriseService.findById(id);

        optEnterprise.ifPresent(e -> {
            model.addAttribute("employees", e.getEmployees());
        });
        model.addAttribute("enterpriseId", id);
        return "enterpriseEmployees";
    }

    @GetMapping("/employee/{id}/transactions")
    public String employeeTransactions(@PathVariable long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());

            model.addAttribute("profile", profile);
            model.addAttribute("isUserAdmin", profile.getRole() == Role.ADMIN);
        }

        Optional<Employee> optEmployee = employeeService.findById(id);

        optEmployee.ifPresent(e -> {
            model.addAttribute("transactions", e.getTransactions());
        });
        model.addAttribute("employeeId", id);
        return "employeeTransactions";
    }

    @GetMapping("/employees-list")
    public String employeesList(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());
            model.addAttribute("profile", profile);
            model.addAttribute("isUserAdmin", profile.getRole() == Role.ADMIN);
        }
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("/enterprise/{id}/transactions")
    public String enterpriseTransactions(@PathVariable long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            Profile profile = this.profileService.getOrCreateProfile(principal.getClaims());
            model.addAttribute("profile", profile);
            model.addAttribute("isUserAdmin", profile.getRole() == Role.ADMIN);
        }

        Optional<Enterprise> optEnterprise = enterpriseService.findById(id);
        
        optEnterprise.ifPresent(e -> {
            float balance = 0;
            Set<Transaction> transactions = e.getTransactions();

            model.addAttribute("transactions", transactions);
            for (Transaction t : transactions) {
                if (t.getConcept().equals("Ingreso"))
                    balance += t.getAmount();
                else if (t.getConcept().equals("Egreso"))
                    balance -= t.getAmount();
            }
            model.addAttribute("transactionsBalance", balance);
        });
        model.addAttribute("enterpriseId", id);
        return "enterpriseTransactions";
    }

    @GetMapping("/app")
    public String application(Model model) {
        return "applicationMenu";
    }
}
