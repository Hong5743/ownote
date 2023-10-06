package com.project.ownote.annual.controller;

import com.project.ownote.annual.dao.AnnualDao;
import com.project.ownote.annual.entity.Annual;
import com.project.ownote.annual.dto.AnnualDto;
import com.project.ownote.annual.dto.AnnualPage;
import com.project.ownote.annual.repository.AnnualEm;
import com.project.ownote.annual.repository.AnnualRepository;
import com.project.ownote.annual.service.AnnualService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class AnnualController {

    @Autowired
    AnnualDao annualDao;
    @Autowired
    AnnualService annualService;
@Autowired
    AnnualRepository annualRepository;
@Autowired
    AnnualEm annualEm;



//    @GetMapping("/write")
//    public String write() {
//
//        return "annual/annualWrite";
//    }

    @GetMapping("/write")
    public String write() {

        return "annual/annualWrite";
    }

    @PostMapping("/annual/write")
    public String write2(Model model, AnnualDto annualDto, @RequestParam String anninfo_code) {

//        LocalTime midnight = LocalTime.MIDNIGHT;


        annualDao.writeAnnual(annualDto);
        return "annual/writeSuccess";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAnnualList(Model model, @RequestParam(value = "pageNo", required = false) String pageNoVal) {

        int pageNo = 1;
        if (pageNoVal != null) {
            pageNo = Integer.parseInt(pageNoVal);
        }
        AnnualPage annualPage = annualService.getAnnualPage(pageNo);

        log.info("-------" + annualPage);
        model.addAttribute("annualPage", annualPage);
        model.addAttribute("pageNo", pageNo);

        return "annual/listtest";
    }




    @RequestMapping(value = "/delete/{annual_id}", method = RequestMethod.GET)
    public String annDelete(@PathVariable Long annual_id, Model model) {
        annualEm.annDeleteById(annual_id);
        return "redirect:/anndata/1";
    }




    @GetMapping("/getAnndataDate")
    @ResponseBody
    public Integer getAnndataDate(@RequestParam("emp_id") Integer emp_id) {
        Integer countdate = annualService.countAnnualdate(emp_id);
        return countdate;
    }



    @RequestMapping(value = "/annModify/{annual_id}", method = RequestMethod.GET)
    public String annModify(@PathVariable Long annual_id, Model model) {
        Annual annual = annualEm.annFindById(annual_id);
        model.addAttribute("annual", annual);
        return "annual/annualModify";
    }
    @RequestMapping(value = "/annualModify", method = RequestMethod.POST)
    @Transactional
    public String update(@ModelAttribute Annual annual,
                         Model model) {
    annualEm.annModify(annual);
        return "redirect:/anndata/1";
    }





    @RequestMapping(value = "/anndataModify/{annual_id}", method = RequestMethod.GET)
    public String anndataModify(@PathVariable Long annual_id, Model model) {
        Annual annual = annualEm.annFindById(annual_id);
        model.addAttribute("annual", annual);
        return "annual/dataModify";
    }








}








