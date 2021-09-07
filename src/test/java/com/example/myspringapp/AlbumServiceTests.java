package com.example.myspringapp;

import com.example.myspringapp.model.Album;
import com.example.myspringapp.service.AlbumService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AlbumServiceTests {
	private AlbumService albumService;
	private String albumId;

	@Before
	public void saveAlbum(){
		Album album = new Album("Jaiv","Jaiv","jaiv@gmail.com",java.time.LocalDate.now());
		this.albumId = albumService.saveAlbum(album).getAlbumId();
	}

	@Test
	public void getAlbumByID(){

		Assert.assertEquals("Jaiv",albumService.getAlbumById(this.albumId).getName());
	}

	@Test
	public void  updateAlbum(){
		Album album = albumService.getAlbumById(albumId);
		album.setName("Jaiv 1");
		albumService.updateAlbum(album);
		Assert.assertEquals("Jaiv",albumService.getAlbumById(this.albumId).getName());
	}

	@After
	public void deleteAlbum(){
		albumService.deleteAlbum(albumId);
	}
}
