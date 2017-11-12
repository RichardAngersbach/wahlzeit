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
	 *
	 */
	public Photo getPhotoFromId(PhotoId id) {
		if (id == null) {
			return null;
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = SkylinePhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}
}
