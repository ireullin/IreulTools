package IreulTools.stringExtension;

import IreulTools.collections.IWrapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ireullin on 2016/10/22.
 */

public class SplitterTest extends TestCase {

        private static final Logger LOG = LoggerFactory.getLogger(SplitterTest.class);

        @Test
        public void testMain() {

            String test = "2016-03-18T9:28:13";
            char[] chars = {'-',':','T','Z'};
            Splitter.from(test).putDelim(chars).each( (wrapper,info) -> {
                LOG.info("{}. {} {}-{}",info.index(),  wrapper.toString(), info.startAt(), info.endAt());
//                if(info.index()==2) return false;
                return true;
            });

            LOG.info("=============================");

            List<IWrapper> arr = Splitter.from(test).putDelim(chars).toList();
            for(IWrapper w : arr){
                LOG.info(w.toString());
            }

        }
}
