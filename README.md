# to-do-api

## Local

### Run

1. 프로젝트를 깃헙을 통해 Download & Open
2. 프로젝트 실행시 원활환 테스트를 위해 testUser와 testTodo 저장 (In-Memory Hibernate Db) 
3. 먼저 testUser1 또는 testUser2 아이디로 (비밀번호 testPw) 로그인 API 를 실행하면 토큰 획득
4. 이후 To-do 혹은 File Api를 실행하기 위해서는 Header 에 Key : X-AUTH-TOKEN, Value : 획득한 토큰 을 입력하면 인가받은 채로 API 실행 가능 (Token 값이 없거나, 유효하지 않으면 AccessDeniedException 발생)
5. File upload 의 경우 프로젝트에 포함되어있는 upload-test-image.png 를 이용하여 테스트 (2mb를 넘지 않는 파일이라면 갖고있는 파일로 테스트해도 무관하지만 그 이상의 크기를 업로드 하면 MaxUploadSizeExceededException 발생 -> application-local.yml 파일에서 업로드 최대 크기 수정)

### Setting

#### Local 환경 설정
- `spring.profiles.active` 속성에 `local` 추가
  - 상단 실행창 Edit Configuration -> TodoApiApplication -> Active Profiles 에 local 입력

## Test
- File, Todo, User 세 가지의 도메인을 나눠서 구현 (13 Test Case)
