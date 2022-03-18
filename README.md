# 프로젝트 라이언 4주차 Basic 미션
**로그인, 회원가입 구현**

Spring Security를 활용하여, 로그인, 회원가입 기능을 구현해 봅시다. 강의에서 사용한 UserDetailsService를 활용하되, 필요한 정보를 다 포함할 수 있도록 합시다.

- `UserEntity` 는 사용자를 저장하기 위해 정의했던 `Entity` 입니다.
  - `username` , `password` 는 일반적인 서비스의 아이디, 비밀번호로 활용됩니다.
  - `residence` 는 `AreaEntity` 를 필요로 합니다.
  - `isShopOwner` 는 회원가입 시에 추가되어야 합니다.
- `CommunityUserDetailsService` 클래스를 정의하고, `UserDetailsService` 의 구현체로 선언합니다.
  - `UserRepository` 를 멤버 객체로 가지고 있어, `loadByUsername` 등의 함수에서 사용할 수 있어야 합니다.
  - 주어진 `username` 에 해당하는 사용자가 없다면, `UsernameNotFoundException` 을 throw 할 수 있도록 작성합니다.
- `UserRepository` 를 통해 받아온 `UserEntity` 를 `UserDetails` 의 형태로 반환할 수 있어야 합니다.
  - `UserDetails` 는 인터페이스로서, Spring Security에서 요구하는 정보를 제공할 수 있는 getter 함수들을 구현하도록 명시되어 있습니다.
  - 강의에서 사용한 미리 구현된 `User` 객체를 사용하거나,
  - 직접 `UserDetails` 를 구현하여, 필요한 내용을 전달하면 됩니다.
- `UserController` 라고 `@Controller` Bean을 만들고, 강의와 유사하게 로그인, 회원가입 등의 기능을 추가합니다.
  - 강의에서 사용한 `signup-form.html` 을 적당히 수정하면, shop owner를 form에 추가할 수 있습니다. `type="checkbox"` 는 `Boolean` 형으로 Controller 에서 받을 수 있습니다.
  - `AreaEntity` 는 편의상 랜덤으로 지정해 줍시다.

```html
<form th:action="@{/user/signup}" method="post">
    <input type="text" name="username" placeholder="아이디"><br>
    <input type="password" name="password" placeholder="비밀번호"><br>
    <input type="password" name="password_check" placeholder="비밀번호 확인"><br>
    is shop owner&nbsp;<input type="checkbox" name="is_shop_owner"><br>
    <button type="submit">회원가입</button>
</form>
```

### 세부 사항

1. `AreaEntity` 의 경우, 더미 데이터를 우선 활용합니다.
  1. 서울시 서초구 서초동, 37.4877° N, 127.0174° E
  2. 서울시 강남구 역삼동, 37.4999° N, 127.0374° E
  3. 서울시 강남구 삼성동, 37.5140° N, 127.0565° E
2. `UserDetailsService` 를 구현할때, `UserEntity` 의 모든 정보가 `UserDetails` 에 포함될 필요는 없습니다. 기본적으로 `UserDetails` 는 인터페이스이며, 정의된 함수들이 다 구현되어 있는 어떤 클래스든 상관없이 사용할 수 있습니다.

# 프로젝트 라이언 4주차 Challenge 미션
Spring Security의 기능은 대부분 Java Servlet Filter를 구성함으로서 만들어집니다. 새로운 Filter를 구현하여, SSO의 초석을 닦아봅시다.

- 기본적인 로그인 기능이 구현된 서버(SSO)를 구성합시다.
  - 강의에서 사용된 `login-form.html` , `signup-form.html` 등을 활용하여도 무방합니다.
  - SSO를 활용하고자 하는 서버로서, Mission 3의 프로젝트를 활용합니다.
- Community Project에 새로운 Filter를 정의합니다.
  - Filter에서 Cookie 정보를 확인하여, `likelion_login_cookie` 가 존재하는지를 확인합니다.
  - 있다면 해당 내용을 로그로 출력하고, 없을경우 없다고 출력합니다.
- `Community Project` 의 임시 홈페이지를 만들고, 로그인 버튼을 추가하여 클릭시 SSO 서버로 Redirect가 진행되도록 만듭니다.
  - 구체적인 경로는 `/request-login` 으로 하고, `Query Parameter` 로 `request_from` 에 마지막 요청 위치가 포함되도록 합니다.
  - 로그인 성공 이후, Cookie에 `likelion_login_cookie` 를 임의의 값으로 추가합시다.
  - 로그인 성공 후  `/request-login` 로, 전달받은 데이터를 잃어버리지 않고 돌아가도록 합니다.
  - 로그인 진행후 `/request-login` 로 돌아와, 본래 요청을 보냈던 `Community Project` 로 Redirect 하도록 구성해 봅시다. 이때, Cookie에 추가한 `likelion_login_cookie` 역시 `Query Parameter` 에 추가합니다.

### 참고 (b, c)
```java
  private final CustomSuccessHandler customSuccessHandler;
        
   public WebSecurityConfig(
           @Autowired CustomUserDetailsService customUserDetailsService,
           @Autowired NaverOAuth2Service oAuth2UserService,
           @Autowired CustomSuccessHandler customSuccessHandler
   ){
       this.userDetailsService = customUserDetailsService;
       this.oAuth2UserService = oAuth2UserService;
       this.customSuccessHandler = customSuccessHandler;
   }
   
//...
               .formLogin()
       .loginPage("/user/login")
       .defaultSuccessUrl("/home")
       .successHandler(customSuccessHandler)
       .permitAll()
//...

```

```java
@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

   @Override
   public void onAuthenticationSuccess(
           HttpServletRequest request,
           HttpServletResponse response,
           Authentication authentication
   ) throws IOException, ServletException {
       response.addCookie(new Cookie("likelion_login_cookie", "test_value"));
       super.onAuthenticationSuccess(request, response, authentication);
   }
}
```

- 앞서 `Communtiy Project` 에서 구성하였던 Filter에서, `(HttpServletRequest) request` 의 `getQueryString()` 에서 `likelion_login_cookie` 를 찾아내, Cookie에 저장하도록 합시다.
- Filter 내부에서 `SecurityContextHolder.getContext()` 가 정상적으로 작동하는지 확인합니다.
  - `Community Project` 에 `spring-boot-starter-security` 를 추가해 두도록 합니다.
  - `SecurityContextHolder.getContext().setAuthentication()` 함수가 잘 호출되는지 확인합니다.
  - 호출한 함수에 `new Authentication() { ... }` 을 인자로 전달하고, 내부 함수를 임시로 구현하여 어플리케이션이 사용자가 로그인 한것으로 판단하는지를 확인해 봅니다.

### 참고

```java
SecurityContextHolder.getContext().setAuthentication(new Authentication() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }
    
        @Override
        public Object getCredentials() {
            return null;
        }
    
        @Override
        public Object getDetails() {
            return null;
        }
    
        @Override
        public Object getPrincipal() {
            return (Principal) () -> "dummy";
        }
    
        @Override
        public boolean isAuthenticated() {
            return true;
        }
    
        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    
        }
    
        @Override
        public String getName() {
            return "dummy";
        }
    });
    chain.doFilter(request, response);
}
```

```java
http
        .authorizeRequests()
        .antMatchers(
                "/home/**",
                "/user/signup/**",
                "/",
                "/css/**",
                "/images/**",
                "/js/**"
        )
        .permitAll()
        .anyRequest()
        .authenticated(
```


### 세부 사항

1. 설명은 복잡해 보이지만, 강의에서 언급한 SSO 구현 방법론을 말로서 풀어 작성한 것입니다. 기본적으로 외부의 다른 서버에 저장되어 있는 사용자의 정보 표현을 일부 가져오는 것을 목표로 합니다.
2. 현재 요구 사항까지 진행할 경우, 아직 SSO 로그인이 진행되지는 않습니다. 로그인 성공 이후 받아오게 되는 `likelion_login_cookie` 를 가지고 실제 로그인한 사용자의 정보를 확인하는 과정이 필요합니다.
3. `AuthenticationSuccessHandler` 는 로그인이 성공한 뒤에만 실행되는, Filter와 유사한 동작을 하는 인터페이스 입니다.
4. `Query Parameter` 는 URL의 구조에 대하여, URL 뒤에 조회 등의 목적으로 추가적인 데이터를 첨부할 때 사용하는 인자입니다. `@GetMapping` 의 `@RequestParam` 으로 확인할 수 있습니다.


# 프로젝트 라이언 3주차 Basic 미션

## Basic Mission

### 요구사항

- 커뮤니티 사이트에 데이터베이스 추가
    - `h2` 데이터베이스를 인메모리 방식으로 추가했습니다.
- DTO를 기반으로 Entity를 만들어 관리해 봅시다.
    - 게시판 서비스를 구성하기 위해 전송하기 위한 DTO, 값을 받기 위한 DTO를 생성했습니다.
- `PostEntity`와`BoardEntity`를 만들어 봅시다.
- `PosetEntity`와`BoardEntity`의 관계를 표현해 봅시다.
    - 하나의 게시판에는 여러 개의 글이 작성될 수 있기 때문에 `BoardEntity` 와 `PostEntity` 는 일대다 관계입니다.
- `@ManyToOne`,`@OneToMany`,`@JoinColumn`을 적절히 사용합시다.
    - `BoardEntity`와 `PostEntity` 사이는 양방향을 이용하지 않고 단방향 매핑만을 사용했습니다. Board를 조회할 때, Post도 같이 조회하기 때문에 `BoardEntity`에서 `PostEntity`의 리스트를 가질 수 있도록 설계했습니다.
    - `UserEntity`와 `PostEntity` 사이에도 마찬가지로 단방향 매핑만을 사용했습니다. `PostEntity`를 조회할 때, User의 정보가 필요하지만, 게시판에서 글을 보여주는 간단한 게시글에서는 `UserEntity`를 조회할 때, 사용자가 작성한 `PostEntity`의 리스트가 필요없다고 생각했기 때문입니다.
- `PostEntity`의 작성자를 저장하기 위한`UserEntity`를 만들고, 마찬가지로 관계를 표현해 봅시다.
    - 하나의 글에는 하나의 작성자만 존재하기 때문에 두 개의 관계는 일대일 관계입니다.

### 세부 조건

- `UserEntity`에 대한 CRUD를 작성합시다.

    ```java
    public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    	@Override
    	<S extends UserEntity> S save(S entity);
    
    	@Override
    	Optional<UserEntity> findById(Long aLong);
    
    	@Override
    	boolean existsById(Long aLong);
    
    	@Override
    	void deleteById(Long aLong);
    }
    ```

- `Post`를 작성하는 단계에서,`User`의 정보를 어떻게 전달할지 고민해 봅시다.

  인증에 성공한 사용자의 ID 값을 이용해 User의 정보를 찾고, 해당 User의 정보를 Post에 저장하는 방식을 채택했습니다.

    ```java
    @Override
    @Transactional
    public PostEntity create(Long boardId, PostEntity post, Long authId) {
    	post.updateUser(memberService.findOne(authId));
    	return postRepository.save(post);
    }
   ```
  
### Create post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 23 28" src="https://user-images.githubusercontent.com/92219795/157366679-7f97f150-31ed-43dd-92c2-d6ab4d3cc0f6.png">

### Find post 
<img width="1206" alt="스크린샷 2022-03-09 오후 12 23 57" src="https://user-images.githubusercontent.com/92219795/157366716-093321d8-eee5-47ca-b9b3-000a0c4ed7ec.png">

### Update post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 24 11" src="https://user-images.githubusercontent.com/92219795/157366737-feeedd99-acb9-4f76-89d4-43717d1f9037.png">

### Delete post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 24 21" src="https://user-images.githubusercontent.com/92219795/157366754-2cfd00e2-c64d-46ad-adf2-d236c8c484de.png">

### Join user
<img width="1206" alt="스크린샷 2022-03-09 오후 12 24 53" src="https://user-images.githubusercontent.com/92219795/157366808-4f91ee0d-8a7e-4255-b816-7499d07e7afe.png">


# 프로젝트 라이언 3주차 Challenge 미션

## 요구사항

- 목적을 가진 커뮤니티 사이트 만들기
- 위치정보를 담기 위한 `AreaEntity` 를 만들어 봅시다.
    1. ‘도, 광역시’, ‘시,군,구’, ‘동,면,읍’ 데이터를 따로 저장할 수 있도록 합시다.
    2. ‘위도’, ‘경도’ 데이터를 저장할 수 있도록 합시다.
- 사용자 정보를 담는 `UserEntity` 를 Basic Mission과 유사하게 만들되, 사용자를 두가지로 분류할 수 있도록 합시다.
    1. 위에 만든 `AreaEntity` 에 대한 정보를 담을 수 있도록 합시다. 이 정보는 자신의 거주지를 담기 위한 정보입니다.
    2. `UserEntity` 는 사용자 하나를 나타내며, 일반 사용자 또는 상점 주인인지에 대한 분류가 되어야 합니다.
- 특정 `UserEntity` 만 가질 수 있는 `ShopEntity` 를 작성합시다. 또, 해당 `ShopEntity` 가 취급하는 품목에 대한 `Cateogory` 를 어떻게 다룰지 생각하여 나타낼 수 있도록 합시다.
    1. `ShopEntity` 는 어디 지역의 상점인지에 대한 정보를 가지고 있어야 합니다.
- 마지막으로 `ShopEntity` 에 대한 게시글인, `ShopPostEntity` 와 `ShopReviewEntity` 를 작성해 봅시다.
    1. `ShopReviewEntity` 는 어떤 사용자든 작성할 수 있으나, `ShopPostEntity` 는 해당 `ShopEntity` 에 대한 주인 `UserEntity` 만 작성할 수 있어야 합니다.

## 세부 조건

- 생성된 테이블의 실제 이름에는 `Entity` 라는 문구가 들어가지 않도록 `@Table` 어노테이션을 활용합시다.
- 변동될 가능성이 있는 데이터와 변동될 가능성이 없는 데이터를 잘 구분하여, `Entity` 작성 여부를 잘 판단합시다.
- `Entity` 를 먼저 구성하되, 시간이 남으면 CRUD까지 구성해 봅시다.

### Find shop post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 25 12" src="https://user-images.githubusercontent.com/92219795/157366837-6b879736-2ac6-4f60-bdd3-4b9c49398641.png">

### Create shop post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 25 22" src="https://user-images.githubusercontent.com/92219795/157366850-21cea7c8-e8b1-4666-bdb2-74eb88f5e72b.png">

### Update shop post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 27 11" src="https://user-images.githubusercontent.com/92219795/157367011-bf3b81b3-ab63-4840-9744-3e2a6d30d6d3.png">

### Delete shop post
<img width="1206" alt="스크린샷 2022-03-09 오후 12 33 50" src="https://user-images.githubusercontent.com/92219795/157367696-90f742e1-939f-4762-bbc5-30dab0810d73.png">


