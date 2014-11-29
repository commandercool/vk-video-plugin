# Serviio plugin for vk.com video content

## What is this for?
[Serviio] (http://serviio.org/) is a free media server. It allows you to stream your media files (music, video or images) to renderer devices (e.g. a TV set, Bluray player, games console or mobile phone) on your connected home network. This plugin allows you to stream video content from vk.com public pages.

## How to use it?
1. First of all you need to download and install [serviio] (http://serviio.org/download).
2. Download plugin script. You can also download it from the [serviio official forum thread] (http://forum.serviio.org/viewtopic.php?f=20&t=17947).
3. Put downloaded plugin script (vk.groovy) into serviio's plugins folder (default: *C:/Program Files/Serviio/plugins*)
4. Run **ServiioConsole.exe**, go to the *Library —> Online Soureces* and click "Add" button. For Source Type, choose "Web Resource". For the Source URL, insert the video URL in the format: http://vk.com/video12345_12345. Set Display name which will be shown on the renderer device. For Media type select "Video" and click "Add" to finish.
5. Go to the Serviio service on your client (e.g. TV) browse to the "Online" folder and find added video by the display name you've set.

## Some notes
* **Supported link type** - For now only links like:
 
> http://vk.com/video{user-id}_{video-id}

are supported. This means you can only add videos from public pages, but not from the search results.
* **Supported players** - Only videos playing in a native vk.com player are supported. Imbedded players (e.g. from youtube or other services) are not supported. So make sure you are adding a valid video.

