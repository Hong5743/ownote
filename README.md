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

<details>
  <summary>2. 로그인</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/6a395282-984e-4db3-8060-9984f837c8d2" width="600" height="400" alt="로그인"/>

```
//Service 코드
public AuthInfo authenticate(String email, String password) {
        Emp emp = empMapper.selectByEmail(email);
        if (emp == null) {
            throw new WrongIdPasswordException();
        }
        if (!emp.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(emp.getEmp_id(),emp.getEmp_num(),dept_name,grade_name,emp.getEmp_password(),emp.getEmp_name(),emp.getEmp_email());
    }
```

로그인 시 DB에서 입력한 아이디와 비밀번호의 정보를 select 하고 존재하지 않으면 Exception을 발생하도록 하였고, 입력한 값이 존재하면 저보들을 AuthInfo에 저장하는 코드를 작성하였습니다.

```
//Controller 코드
@RequestMapping(value = "/emp/loginSucess", method = RequestMethod.POST)
    public String PostLogin(@RequestParam("emp_email") String emp_email, @RequestParam("emp_password") String emp_password, HttpSession session) {
        String email = emp_email + "@ownote.com";
        AuthInfo authInfo = authService.authenticate(email, emp_password);
        session.setAttribute("authInfo", authInfo);

    return "/emp/loginSucess";
    }
```

서비스 컨트롤러 코드를 거쳐 로그인에 성공하면 서비스 코드에서 작성한 AuthInfo로 변수를 설정하여 세션으로 등록하도록 하였습니다.
</details>

<details>
  <summary>3. 사원 주소록</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/964f51b2-945b-49c0-b079-51b5c5998de6" width="600" height="400" alt="사원 주소록"/>

```
@RequestMapping(value = "/emp/adress", method = RequestMethod.GET)
    public String getEmpAddress(Model model, @RequestParam(name = "pageNo", required = false) String pageNo) {
        int pageSize = 4;
        int pageNum = 1;
        try{
            if (pageNo != null) {
                pageNum = Integer.parseInt(pageNo);
            }
        } catch (NumberFormatException e) {
            pageNum = 1;
        }
        EmpAdressPage empAdressPage = getEmpAdressPage(pageNum, pageSize);
        model.addAttribute("listPage", empAdressPage);
        return "emp/adress";
    }
```

그룹 웨어에서 반드시 필요한 사원간의 원활한 소통을 위해서 사원 주소록을 구현하였고 Paging 처리를 하여 가독성을 높였습니다. 
</details>
<details>
  <summary>4. 사원 마이 페이지</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/89a6f9ef-c46c-4a13-87d3-ca35fa90ed4a" width="600" height="400" alt="사원 마이 페이지"/>

  ```
  @RequestMapping(value = "/emp/myPage", method = RequestMethod.GET)
    public String myPage(Model model, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        String emp_email = authInfo.getEmp_email();
        Emp myPage = myPageService.selectByEmailForMyPage(emp_email);
        model.addAttribute("myPage", myPage);
        return "emp/myPage";
    }
  ```

로그인 성공시 메인페이지 우측 상단에 세션에 담겨있는 사용자 이름이 화면에 나타나게 되며 이를 클릭시 마이 페이지로 이동하게 되며 자신의 정보를 수정할수 있게 수정 버튼을 클릭하면 개인정보 수정 폼으로 이동하게 됩니다.
</details>
<details>
  <summary>5. 이벤트 게시판</summary>
  <img src="https://github.com/Hong5743/ownote/assets/136396772/e3194130-bd8a-444f-ab01-40d0ae31c3b5" width="600" height="400" alt="이벤트 게시판"/><br>
  
매달 각 부서별로 이벤트 게시판에 이벤트 참여 게시글을 올리고 좋아요를 가장 많이 받는 부서에 회식비 지원이 나가는 이벤트를 진행합니다. 글쓰기를 누르면 이벤트 참여 게시글을 작성 할 수 있으며 좋아요 순으로 순위가 나뉘게 때문에 수정은 불가하고 삭제만 가능하게 만들었습니다.
<br>
<img src="https://github.com/Hong5743/ownote/assets/136396772/5c043580-2ecb-4436-a69f-6e00b2a42e15" width="600" height="400" alt="이벤트 게시판 작성"/>

```
 @GetMapping("/musicContest/write")
    public String insertMusicContestG(HttpSession session, Model model){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        model.addAttribute("authInfo", authInfo);
        return "musicContest/musicContestWriteForm";
    }

    @PostMapping("/musicContest/list")
    public String insertMusicContest(MusicContestDto musicContestDto){
        musicContestService.insertMusicContest(musicContestDto);
        return "redirect:/musicContest/list";
    }
```

영상 삽입 방법은 에디터에 내장된 영상 업로드 기능을 사용하였고, 박스 안 번튼을 클릭하면 유튜브 url을 입력하면 영상이 삽입이 됩니다.
<br>
<img src="https://github.com/Hong5743/ownote/assets/136396772/ffb2d8b4-a8b7-4a1c-b9c1-2824ca9f0bee" width="600" height="400" alt="이벤트 게시판 좋아요"/>

```
@PostMapping("/musicContest/like")
    @Transactional
    public String getIncreaseLike(@RequestParam(value = "musiccontest_id") int musiccontest_id,
                                  HttpSession session, Model model, LikeDto likeDto){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        LikeDto dto = likeService.selectLike(musiccontest_id);
        System.out.println("-----------------------------------------"+dto);
        likeDto.setEmp_id(authInfo.getEmp_id());
        System.out.println("987987987897987989"+likeDto);
        if(dto == null) {
            likeService.increaseLike(musiccontest_id);
            likeService.insertLike(likeDto);
            model.addAttribute("dto", dto);
            model.addAttribute("authInfo", authInfo);
            return "redirect:/musicContest/list";
        } else {
            return "redirect:/musicContest/list?error=1";
        }
    }
```

사원들마다 좋아요를 게시글 당 한 번만 누를수 있게 Transection을 사용해 구현하여 중복 투표를 방지하였습니다.
</details>

## 5. 아쉬운 점
사원 주소록에 검색 기능을 추가하지 못해 사원들의 수가 증가할수록 찾는데 불편함이 증가하였으며, 유튜브 영상을 받아오는 API 사용에 실패하여 다음에 한번 사용을 해보고 싶으며 API 사용법에 대해 계속 숙달해 나가겠습니다.




