package com.marketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.dto.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;

@Controller
public class leadController {
	@Autowired
	private LeadService leadService;

	// Handler methods
	@RequestMapping("/createlead")
	public String viewCreateLeadPage() {
		return "create_lead";
	}

	// @RequestMapping("/savelead")
//	public String saveOneLead(@ModelAttribute("lead") Lead lead,ModelMap model){
	// leadService.saveLead(lead);
	// model.addAttribute("msg", "Lead is Saved!!");
	// return"create_lead";

	// }
	// @RequestMapping("/savelead")
	// public String saveOneLead(@RequestParam("name") String
	// fName,@RequestParam("lastName") String lName,@RequestParam("emailId") String
	// email,@RequestParam("mobileNumber") long mobile){
	// Lead l=new Lead();
	// l.setFirstName(lName);
	// l.setLastName(lName);
	// l.setEmail(email);
	// l.setMobile(mobile);
	// leadService.saveLead(l);
	// return"create_lead";
	// }
	/*
	 * @RequestMapping("/savelead") public String saveOneLead(LeadData data,
	 * ModelMap model) { Lead l=new Lead(); l.setFirstName(data.getFirstName());
	 * l.setLastName(data.getLastName()); l.setEmail(data.getEmail());
	 * l.setMobile(data.getMobile()); leadService.saveLead(l);
	 * model.addAttribute("msg", "Lead is Saved!!"); return "create_lead";
	 * 
	 * }
	 */
	@RequestMapping("/savelead")
	public String saveOneLead(@ModelAttribute("lead") Lead lead,ModelMap model){
		 leadService.saveLead(lead);
		 model.addAttribute("msg", "Lead is Saved!!");
		 return"create_lead";

	}
	@RequestMapping("/listall")
	public String listAllLeads(ModelMap model) {
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads", leads);
		return"lead_search_result";
				
			}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") long id,ModelMap model) {
		leadService.deleteLeadById(id);
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads", leads);
		return"lead_search_result";
	}
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id") long id,ModelMap model) {
		Lead lead = leadService.getOnelead(id);
		model.addAttribute("lead", lead);
		return"update_lead";
		
	}
	@RequestMapping("/updatelead")
	public String updateOneleaddata(LeadData data,ModelMap model) {
		Lead lead=new Lead();
		lead.setId(data.getId());
		lead.setFirstName(data.getFirstName());
		lead.setLastName(data.getLastName());
		lead.setEmail(data.getEmail());
		lead.setMobile(data.getMobile());
         leadService.saveLead(lead);
         List<Lead> leads = leadService.listLeads();
 		model.addAttribute("leads", leads);
 		return"lead_search_result";
	}
}
