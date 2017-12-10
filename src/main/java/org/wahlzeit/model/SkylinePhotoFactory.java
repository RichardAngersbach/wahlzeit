package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

public class SkylinePhotoFactory extends PhotoFactory {
	
	private static final Logger log = Logger.getLogger(SkylinePhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static SkylinePhotoFactory instance = null;
	
	public SkylinePhotoFactory() {
		super();
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized SkylinePhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
			setInstance(new SkylinePhotoFactory());
		}
		return instance;
	}

	/**
	 * Method to set the singleton instance of PhotoFactory.
	 * @throws IllegalStateException when an instance already exists
	 */
	protected static synchronized void setInstance(SkylinePhotoFactory photoFactory) throws IllegalStateException {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	public SkylinePhoto createPhoto() {
		return new SkylinePhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	public SkylinePhoto createPhoto(PhotoId id) {
		return new SkylinePhoto(id);
	}

	/**
	 * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
	 * Google Cloud storage.
	 */
	public SkylinePhoto loadPhoto(PhotoId id) {
	   /* Photo result =
                OfyService.ofy().load().type(SkylinePhoto.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
		return null;
	}
}
