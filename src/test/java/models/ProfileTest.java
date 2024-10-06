package models;

import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.profiles.models.Post;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.users.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ProfileTest {

    @Test
    public void testProfileConstructorAndGetters() {
        User user = mock(User.class);
        Profile profile = new Profile(1L, user);
        
        assertThat(profile.getId(), equalTo(1L));
        assertThat(profile.getUser(), equalTo(user));
    }

    @Test
    public void testSetUser() {
        User user = mock(User.class);
        Profile profile = new Profile();
        profile.setUser(user);
        
        assertThat(profile.getUser(), equalTo(user));
    }

    @Test
    public void testSetId() {
        Profile profile = new Profile();
        profile.setId(2L);
        
        assertThat(profile.getId(), equalTo(2L));
    }

    @Test
    public void testSetPosts() {
        Profile profile = new Profile();
        List<Post> posts = new ArrayList<>();
        Post post = mock(Post.class);
        posts.add(post);
        
        profile.setPosts(posts);
        
        assertThat(profile.getPosts().size(), equalTo(1));
        assertThat(profile.getPosts().get(0), equalTo(post));
    }

    @Test
    public void testAddAndRemovePosts() {
        Profile profile = new Profile();
        Post post1 = mock(Post.class);
        Post post2 = mock(Post.class);
        
        profile.getPosts().add(post1);
        profile.getPosts().add(post2);
        
        assertThat(profile.getPosts().size(), equalTo(2));
        
        profile.getPosts().remove(post1);
        
        assertThat(profile.getPosts().size(), equalTo(1));
        assertThat(profile.getPosts().get(0), equalTo(post2));
    }
}