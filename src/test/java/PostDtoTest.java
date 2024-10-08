import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;
import com.personal.petcare_backend.profiles.postdto.PostDto;

public class PostDtoTest {

    @Test
    public void testPostDtoConstructorAndGetters() {
        // Given
        Long id = 1L;
        String title = "Post Title";
        String content = "Post content goes here.";
        String imageUrl = "http://image.url/sample.jpg";

        // When
        PostDto postDto = new PostDto(id, title, content, imageUrl);

        // Then
        assertThat(postDto.getId(), equalTo(id));
        assertThat(postDto.getTitle(), equalTo(title));
        assertThat(postDto.getContent(), equalTo(content));
        assertThat(postDto.getImageUrl(), equalTo(imageUrl));
    }

    @Test
    public void testPostDtoSetters() {
        // Given
        PostDto postDto = new PostDto();

        // When
        postDto.setId(2L);
        postDto.setTitle("Another Title");
        postDto.setContent("Different content.");
        postDto.setImageUrl("http://image.url/another.jpg");

        // Then
        assertThat(postDto.getId(), equalTo(2L));
        assertThat(postDto.getTitle(), equalTo("Another Title"));
        assertThat(postDto.getContent(), equalTo("Different content."));
        assertThat(postDto.getImageUrl(), equalTo("http://image.url/another.jpg"));
    }

    @Test
    public void testPostDtoNoArgsConstructor() {
        // When
        PostDto postDto = new PostDto();

        // Then
        assertThat(postDto, notNullValue());
        assertThat(postDto.getId(), equalTo(null));
        assertThat(postDto.getTitle(), equalTo(null));
        assertThat(postDto.getContent(), equalTo(null));
        assertThat(postDto.getImageUrl(), equalTo(null));
    }
}
