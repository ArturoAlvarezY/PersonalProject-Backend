package models;

import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class PostTest {

    @Test
    public void testPostConstructorAndGetters() {
        Profile profile = mock(Profile.class);
        Post post = new Post(1L, "Post Title", "Post Content", "http://image.url", profile);
        
        assertThat(post.getId(), equalTo(1L));
        assertThat(post.getTitle(), equalTo("Post Title"));
        assertThat(post.getContent(), equalTo("Post Content"));
        assertThat(post.getImageUrl(), equalTo("http://image.url"));
        assertThat(post.getProfile(), equalTo(profile));
    }

    @Test
    public void testSetTitle() {
        Post post = new Post();
        post.setTitle("New Title");
        
        assertThat(post.getTitle(), equalTo("New Title"));
    }

    @Test
    public void testSetContent() {
        Post post = new Post();
        post.setContent("New Content");
        
        assertThat(post.getContent(), equalTo("New Content"));
    }

    @Test
    public void testSetImageUrl() {
        Post post = new Post();
        post.setImageUrl("http://new.image.url");
        
        assertThat(post.getImageUrl(), equalTo("http://new.image.url"));
    }

    @Test
    public void testSetProfile() {
        Profile profile = mock(Profile.class);
        Post post = new Post();
        post.setProfile(profile);
        
        assertThat(post.getProfile(), equalTo(profile));
    }

    @Test
    public void testSetId() {
        Post post = new Post();
        post.setId(2L);
        
        assertThat(post.getId(), equalTo(2L));
    }
}