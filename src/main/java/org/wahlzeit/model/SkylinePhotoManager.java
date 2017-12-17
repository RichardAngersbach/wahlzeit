package org.wahlzeit.model;

import java.util.logging.Logger;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SkylinePhotoManager extends PhotoManager {
	/**
	 *
	 */
	protected static final SkylinePhotoManager instance = new SkylinePhotoManager();

	private static final Logger log = Logger.getLogger(SkylinePhotoManager.class.getName());

	/**
	 *
	 */
	public SkylinePhotoManager() {
		photoTagCollector = SkylinePhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	/**
	 * @throws IllegalArgumentException when argument id is null or SkylinePhotoFactory couldn't load a photo
	 */
	public Photo getPhotoFromId(PhotoId id) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("Argument Photo Id is null");
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = SkylinePhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			} else {
				throw new IllegalArgumentException("Couldn't load a photo from id");
			}
		}

		return result;
	}
}
