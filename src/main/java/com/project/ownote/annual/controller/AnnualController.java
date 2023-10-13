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

import java.util.List;

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
    private int size = 10;

    public AnnualPage getAnnualPage (int pageNum, int pageSize){
        int total = annualDao.countAnnual();
        List<AnnualDto> content = annualDao.annualAllList((pageNum -1) * size, size);

        return new AnnualPage(total, pageNum,size,content);
    }


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
    public String getAnnualList(Model model, @RequestParam(name = "pageNo", required = false) String pageNo) {
        int pageSize = 4; // 페이지 크기 설정 (한 페이지에 보여줄 회원 수)
        int pageNum = 1;

        if (pageNo != null) {
            pageNum = Integer.parseInt(pageNo);
        }
        AnnualPage annualPage = getAnnualPage(pageNum, pageSize);

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








