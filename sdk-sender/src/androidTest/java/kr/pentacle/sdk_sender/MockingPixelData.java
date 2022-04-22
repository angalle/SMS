package kr.pentacle.sdk_sender;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MockingPixelData {

    public static String PIXEL_RESPONSE_ONE_DESTINATION = "[\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "        {\n" +
            "            \"destinationId\": 172, \n" +
            "            \"template_mappers\": [\n" +
            "            {\n" +
            "                \"contentId\": \"1\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPGoogleAdsProvider\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"segmentId\": \"2773\"\n" +
            "            }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"destinationId\": 171, \n" +
            "            \"template_mappers\": [\n" +
            "            {\n" +
            "                \"contentId\": \"2\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPKaKaoProvider\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"tag\": \"2774\"\n" +
            "            }\n" +
            "            ]\n" +
            "        }, \n" +
            "        {\n" +
            "            \"destinationId\": 173, \n" +
            "            \"template_mappers\": [\n" +
            "            {\n" +
            "                \"contentId\": \"3\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPFacebookProvider\"\n" +
            "            }, \n" +
            "            {\n" +
            "                \"segmentId\": \"2775\"\n" +
            "            }\n" +
            "            ]\n" +
            "        }\n" +
            "        ], \n" +
            "        \"segmentId\": 2774\n" +
            "    }\n" +
            "]";


    public static String PIXEL_RESPONSE_TWO_DESTINATION = "[\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "            {\n" +
            "                \"destinationId\": 173,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"1\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPFacebookProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"segmentId\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"segmentId\": 2774\n" +
            "    },\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "            {\n" +
            "                \"destinationId\": 172,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"11\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPGoogleAdsProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"segmentId\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"destinationId\": 171,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"1\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPKaKaoProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"tag\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"segmentId\": 2774\n" +
            "    }\n" +
            "]";


    public static String PIXEL_RESPONSE_THREE_DESTINATION = "[\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "            {\n" +
            "                \"destinationId\": 173,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"1\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPFacebookProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"segmentId\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"segmentId\": 2774\n" +
            "    },\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "            {\n" +
            "                \"destinationId\": 173,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"1\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPGoogleAdsProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"segmentId\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"segmentId\": 2774\n" +
            "    },\n" +
            "    {\n" +
            "        \"destinations\": [\n" +
            "            {\n" +
            "                \"destinationId\": 171,\n" +
            "                \"template_mappers\": [\n" +
            "                    {\n" +
            "                        \"contentId\": \"1\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"trigger\": \"kr.pentacle.sdk_sender.HyperDMPKaKaoProvider\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"tag\": \"2774\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"segmentId\": 2774\n" +
            "    }\n" +
            "]";
}