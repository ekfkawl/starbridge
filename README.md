![logo_head](https://user-images.githubusercontent.com/99597985/165504535-f40d4f23-07a0-41fb-a3ad-a34e0c455302.svg)
----------
#### LIVE: https://sbr.kr
* [스타브리지란 무엇입니까?](#스타브리지란-무엇입니까)
* [스타브리지는 어떻게 작동합니까?](#스타브리지는-어떻게-작동합니까)
* [스타브리지가 제공하는 기능은 무엇입니까?](#스타브리지가-제공하는-기능은-무엇입니까)
* [사용 기술 스택](#사용-기술-스택)
* [ERD](#ERD)
* [배포 환경](#배포-환경)
* [데모 시작](#데모-시작)
----------

## 스타브리지란 무엇입니까?
스타브리지는 온라인 게임 [스타크래프트 리마스터](https://starcraft.com)에 다양한 플러그인(부가기능)을 적용할 수 있게 해주는 런처입니다.<br/>
기존 서비스의 단점을 보완하여 더 나은 환경을 제공하는 것이 목적입니다.<br/><br/>

## 스타브리지는 어떻게 작동합니까?
![21512412](https://user-images.githubusercontent.com/99597985/165559035-735184b8-bd7e-4f0a-a0bf-454d14768b04.png)<br/>
스타브리지는 [Web Application](https://sbr.kr)과 [Windows Application](https://sbr.kr/install) 각 플랫폼이 상호작용합니다.<br/><br/>
* Web Application
  * 서비스 동작에 필요한 데이터를 사용자가 직접 등록/수정할 수 있는 환경을 제공합니다.
  * 사용자 데이터를 종합하여 JSON 형식으로 반환해 주는 API를 제공합니다.
* Client (Windows Application)
  * 리버싱 엔지니어링(DLL Injection)을 통하여 새로운 서비스 로직을 생성합니다.
  * 서비스에 응용될 데이터는 IdHTTP 컴포넌트로 웹 API를 호출하여 실시간 매핑합니다.
> SW 부분은 악용될 가능성이 존재하여 오픈소스로 제공되지 않습니다.
   <br/>
   
## 스타브리지가 제공하는 기능은 무엇입니까?
* 방 상단 노출화
  > 현재 시스템은 방 생성 시 스킨 보유 상태 및 플레이 시간 등에 의해 노출 우선순위가 결정되는 차별적 요소가 존재합니다.<br/>
  > 스타브리지는 자동으로 상단으로 노출되게 하여 빠른 진행과 게임 활성화를 도와줍니다.
* 플레이어 정보 상세 조회
  > 전용 커스텀 뷰를 제공해 줍니다. 이는 게임에 참여한 플레이어의 전적, 배틀태그, 해시, 핑 상태 등을 조회할 수 있습니다.<br/>
  > 플레이어의 아이디가 바뀌더라도 식별 가능한 고유값을 통해 블랙리스트 관리 및 비매너 플레이어 판별에 용이합니다.
* 블랙리스트 등록
  > 아이디의 고유값인 배틀태그를 블랙리스트로 지정하여 특정 플레이어 자동 추방(차단)을 지원합니다.
* 인게임 강티 투표 시스템
  > 현재 시스템은 인게임 내에서는 추방(차단)이 불가능합니다.</br>
  > 스타브리지는 특정 플레이어에 대한 추방 투표를 진행할 수 있는 플러그인을 제공하여, 인게임 내에서도 악성 플레이어를 차단할 수 있는 시스템을 지원합니다.</br>
* 다양한 플러그인
  > 외에도 다양한 플러그인을 제공하고 있으며 지속적으로 추가 중에 있습니다.
   <br/>
   
* 테스트 영상은 아래 유튜브 링크에서 시청할 수 있습니다.</br></br>
  [![test starbridge](http://img.youtube.com/vi/nCeNGw5W7ew/0.jpg)](https://www.youtube.com/watch?v=nCeNGw5W7ew)
<br/><br/>
## 사용 기술 스택
* Spring Boot
* Gradle
* Java 8
* MySQL
* Spring Data JPA
* Spring Security
* JQuery
* Thymeleaf
* Pascal
* Assembly x86<br/><br/>

## ERD
![db](https://user-images.githubusercontent.com/99597985/165548999-fc6003bc-77bf-4844-9914-3fd550675084.PNG)<br/><br/>

## 배포 환경
* [Cafe24](https://www.cafe24.com) Tomcat JSP 호스팅 사용
* [가비아](https://domain.gabia.com/) MySQL DB 호스팅 사용<br/><br/>

## 데모 시작
* Configurations Add VM Options -Dspring.profiles.active=dev
