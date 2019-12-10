package com.appavoc.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.VerificationForm;
import com.appavoc.service.impl.VerificationTokenService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/Mail")
public class MailController {
	@Autowired
    VerificationTokenService verificationTokenService;
	
	@GetMapping("/")
    public String index() {
        return "redirect:/email-verification";
    }

    @GetMapping("/email-verification")
    public String formGet(Model model) {
        model.addAttribute("verificationForm", new VerificationForm());
        return "verification-form";
    }

	/*
	 * @PostMapping("/email-verification") public String formPost(@Valid
	 * VerificationForm verificationForm, BindingResult bindingResult, Model model)
	 * { if (!bindingResult.hasErrors()) { model.addAttribute("noErrors", true); }
	 * model.addAttribute("verificationForm", verificationForm);
	 * 
	 * verificationTokenService.createVerification(verificationForm.getEmail());
	 * return "verification-form"; }
	 */

    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) throws Exception {
        return verificationTokenService.verifyEmail(code).getBody();
    }

}
