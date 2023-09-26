package com.project.ownote.annual.controller;

import com.project.ownote.annual.dao.AnnualDao;
import com.project.ownote.annual.dto.AnnualDto;
import com.project.ownote.annual.dto.AnnualPage;
import com.project.ownote.annual.repository.AnnualRepository;
import com.project.ownote.annual.service.AnnualService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class AnnualController {

    @Autowired
    AnnualDao annualDao;
    @Autowired
    AnnualService annualService;
@Autowired
    AnnualRepository annualRepository;

    @GetMapping("/write")
    public String write() {

        return "annual/annualWrite";
    }

    @PostMapping("/annual/write")
    public String write2(Model model, AnnualDto annualDto, @RequestParam String anninfo_code) {

//        LocalTime midnight = LocalTime.MIDNIGHT;

        int anncode = annualDao.codeId(anninfo_code);
        annualDto.setAnnual_time(anncode);
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

        return "annual/list";
    }

    @GetMapping("/getAnnualTime")
    public ResponseEntity<Map<String, Object>> getAnnualTime(@RequestParam("anninfo_code") int anninfo_code) {
        Map<String, Object> response = new HashMap<>();

        Double annual_time = 0.0;

        switch (anninfo_code) {
            case 1 :
                annual_time = 0.0; // 연차 선택 시 수량은 0

            case 2:
                annual_time = 1.0; // 연차 선택 시 수량은 1
                break;
            case 3:
                annual_time = 0.5; // 반차 선택 시 수량은 0.5
                break;
            case 4:
                annual_time = 0.25; // 반반차 선택 시 수량은 0.25
                break;
            case 5:
            case 6:
            case 7:
                annual_time = 1.0; // 다른 선택지에 대한 수량
                break;
        }

        response.put("annualTime", annual_time);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/saveAnnualTime") // POST 요청 처리
    public ResponseEntity<String> saveAnnualTime(@RequestParam("anninfo_code") int anninfo_code) {
        try {
            // 여기서 연차 수량을 계산하거나 원하는 로직을 수행합니다.
            double annual_time = calculateAnnualTime(anninfo_code);

            // DB에 저장
            annualService.saveAnnualTime(annual_time);

            return ResponseEntity.ok("연차 수량이 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("연차 수량 저장 중 오류 발생: " + e.getMessage());
        }
    }

    private double calculateAnnualTime(int anninfo_code) {
        // 연차 수량 계산 로직을 구현하세요.
        double annual_time = 0.0;
        switch (anninfo_code) {
            case 1:
                annual_time = 1.0;
                break;

            case 2:
                annual_time = 0.5;
                break;
            case 3:
                annual_time = 0.25;
                break;
            case 4:
                annual_time = 1.0;
                break;
            case 5:
            case 6:
                annual_time = 1.0;
                break;

            // 다른 연차 유형에 대한 처리도 추가할 수 있습니다.
        }
        return annual_time;
    }

}
