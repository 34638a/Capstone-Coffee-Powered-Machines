package au.com.qut.cpm.capstone.system.io.socialmedia;

import au.com.qut.cpm.capstone.system.publication.Publication;

public interface SocialMediaEventPublisher {


    public boolean publishEventToFeed(Publication publication);
}
