## Basic Mission

### 요구사항

- 커뮤니티 사이트에 데이터베이스 추가
    - `h2` 데이터베이스를 인메모리 방식으로 추가했습니다.
- DTO를 기반으로 Entity를 만들어 관리해 봅시다.
    - 게시판 서비스를 구성하기 위해 전송하기 위한 DTO, 값을 받기 위한 DTO를 생성했습니다.
- `PostEntity`와`BoardEntity`를 만들어 봅시다.
  - 
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