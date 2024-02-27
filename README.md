# Ownote
>Ownote 사원들만을 위한 사내 그룹웨어, 출 퇴근 관리 및 주소록을 통한 타 부서와의 협업 능률 향상, 사내 동아리 설립을 통한 유대감 형성, 매달 초 게시판 활동을 통한 회식비 지원 이벤트를 통한 사원들의 애정도 상승 효과를 기대하는 이 모든 것을 포함한 사내 그룹웨어 입니다.


## 1. 프로젝트 개요
* 프로젝트 기간: 2023.09.18 ~ 2023.10.20   
* 개발 인원:  5명
* 프로젝트 목적: 기업에 대한 이해를 위해

## 2. 기술 스택
Back: JAVA, SPRING BOOT, MYBATIS, MYSQL<br>
Front: HTML, CSS3, JS<br>
협업: [Notion](https://www.notion.so/9-26-DB-5a6c25a562b2449dab90282925402294), [Slack](https://company-5yw1982.slack.com/ssb/redirect)<br>

## 3. 프로젝트 산춘물
<details>
  <summary>ERD</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/9d6cc5f1-b41c-4d58-8207-68da54464945" width="600" height="400" alt="ERD"/>
</details>

## 4. 구현 기능
<details>
  <summary>1. 사원 등록</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/52134a52-e24b-4d33-b604-0c9b0c590b99" width="600" height="400" alt="사원 등록"/>
  Http 메소드 중 하나인 Post 메소드를 사용하여 입력한 데이터들이 DB에 Insert 되도록 설계하였습니다.

  ```
@RequestMapping(value = "/emp/logIn", method = RequestMethod.POST)
    public String postSignUp(@ModelAttribute SignUpDto signUpDto, RedirectAttributes redirectAttributes) {
        try {
            Integer emp_num = signUpDto.getEmp_num();
            if (emp_num == null) {
                signUpDto.setEmp_num(currentEmpNum);
            }
            if (!signUpDto.getEmp_email().contains("@")) {
                signUpDto.setEmp_email(signUpDto.getEmp_email() + "@ownote.com");
            } else {
                redirectAttributes.addFlashAttribute("error", "영문 숫자 조합만 입력이 가능합니다.");
            }
            signUpService.insertEmp(signUpDto);
            return "/emp/loginForm";
        } catch (Exception e) {
            // 회원가입 실패 처리
            redirectAttributes.addFlashAttribute("error", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return "redirect:/emp/signUp";
        }
    }
```


  
</details> 
#### 사원 주소록
#### 사원 마이 페이지
#### [인사팀]사원등록
#### 메인 이벤트 게시판
#### Layout CSS





