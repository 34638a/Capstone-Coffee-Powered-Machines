package au.com.qut.cpm.capstone.system.io.rss;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.rss.Channel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialGatewayRSSAtom {

    @GetMapping(path = "/rss")
    public Channel rss() {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("Mining Events Info Feed");
        channel.setDescription("Articles posted to the events feed.");
        channel.setLink("https://www.miningevents.info");
        channel.setUri("https://www.miningevents.info");
        channel.setGenerator("In House Programming");
        //Like more Entries here about different new topics
        return channel;
    }

    @GetMapping(path = "/atom")
    public Feed atom() {
        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        feed.setTitle("Mining Events Info Feed");
        feed.setId("https://www.miningevents.info");

        Content subtitle = new Content();
        subtitle.setType("text/plain");
        subtitle.setValue("Articles posted to the events feed.");
        feed.setSubtitle(subtitle);

        return feed;
    }
}
