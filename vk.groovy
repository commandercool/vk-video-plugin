import org.serviio.library.online.*
import groovy.json.*


/**
* <h1>vk.com Serviio plugin</h1>
* 
* @version 1
* @author commandercool
*/

class Vk extends WebResourceUrlExtractor {

    final Integer VERSION = 1;
    final String VALID_URL = /http:\/\/vk.com\/video(\d+)_(\d+)/;
    final String NVARS = /nvar vars = (\{.*\});/;
    final String[] quals = ['240', '360', '720', '1080'];

    int getVersion() {
        return VERSION;
    }
    
    String getExtractorName() {
        return 'vk.com'
    }
    
    boolean extractorMatches(URL feedUrl) {
        return feedUrl ==~ VALID_URL
    }
    
    WebResourceContainer extractItems(URL resourceUrl, int maxItemsToRetrieve) {
        def container = new WebResourceContainer()
        def http = resourceUrl.text;
        def jsonstr = (http =~ NVARS)[0][1]
        jsonstr = jsonstr.replaceAll('\\\\', '')
        def json = new JsonSlurper().parseText(jsonstr)
        log("${json}")
        def title = "${json.md_title}"
        def items = []
        for (qual in quals) {
            def url = json."url${qual}"
            log(url)
            if (url != null) {
                def fulltitle = title + " [${qual}p]"
                def item = new WebResourceItem(title: fulltitle, additionalInfo: [
                expiresImmediately: false,
                cacheKey: fulltitle,
                url: url,
                thumbnailUrl: json.jpg,
                live: false
                ])
                items += item        
            }
        }
        container.setTitle(title)
        container.setItems(items)
        return container
    }

    ContentURLContainer extractUrl(WebResourceItem arg0, PreferredQuality arg1) {
        def c = new ContentURLContainer()
        if(arg0 != null) {
            c.setExpiresImmediately(arg0.additionalInfo.expiresImmediately)
            c.setCacheKey(arg0.additionalInfo.cacheKey)
            c.setContentUrl(arg0.additionalInfo.url)
            c.setLive(arg0.additionalInfo.live)
            c.setThumbnailUrl(arg0.additionalInfo.thumbnailUrl)
        }
        return c
    }

    static void main(args) {
        Vk vk = new Vk()
        def url = "http://vk.com/video3007616_164708547"
        
        println vk.extractorMatches(new URL(url))
        
        vk.extractItems(new URL(url), 123).getItems().each { it->
            ContentURLContainer result = vk.extractUrl(it, PreferredQuality.HIGH)
            println result
        }
    }
    
}