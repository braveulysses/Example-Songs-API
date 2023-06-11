package org.jacob.example.songs;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jacob.example.songs.controller.BandController;
import org.jacob.example.songs.controller.LabelController;
import org.jacob.example.songs.controller.SongController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SongsApiApplicationTest {

	@Autowired
	private BandController bandController;

	@Autowired
	private LabelController labelController;

	@Autowired
	private SongController songController;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(bandController).isNotNull();
		assertThat(labelController).isNotNull();
		assertThat(songController).isNotNull();
	}

	@Test
	public void getAllBands() throws Exception {
		mockMvc.perform(get("/bands"))
						.andDo(print())
						.andExpect(status().isOk());
	}

	@Test
	public void getAllLabels() throws Exception {
		mockMvc.perform(get("/labels"))
						.andDo(print())
						.andExpect(status().isOk());
	}

	@Test
	public void getAllSongs() throws Exception {
		mockMvc.perform(get("/songs"))
						.andDo(print())
						.andExpect(status().isOk());
	}
}
