
/*CKEDITOR.replace( 'editor', {
    customConfig: '/teamnote/ckeditor/js/config.js',
    contentsCss: '/teamnote/ckeditor/css/contents.css',
    skin: 'bootstrapck,/teamnote/ckeditor/skins/bootstrapck/'
});*/

CKEDITOR.replace( 'editor', {
    toolbar: [
        { name: 'clipboard', items: [ 'Undo', 'Redo' ] },
        { name: 'styles', items: [ 'Styles', 'Format' ] },
        { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Strike', '-', 'RemoveFormat' ] },
        { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
        { name: 'links', items: [ 'Link', 'Unlink' ] },
        { name: 'insert', items: [ 'Image', 'Table' ] },
        { name: 'tools', items: [ 'Maximize' ] },
        { name: 'editing', items: [ 'Scayt' ] }
    ],
    customConfig: '',
    // Enabling extra plugins
    extraPlugins: 'autoembed,image2,uploadimage,uploadfile',
    /*********************** File management support ***********************/
    filebrowserUploadUrl: '/teamnote/uploadNoteImage',
    /*********************** File management support ***********************/
    removePlugins: 'image,elementspath',
    // Make the editing area bigger than default.
    height: 461,
    tabSpaces: 8,
    skin: 'bootstrapck,/teamnote/ckeditor/skins/bootstrapck/',
    // An array of stylesheets to style the WYSIWYG area.
    // Note: it is recommended to keep your own styles in a separate file in order to make future updates painless.
    contentsCss: [ 'https://cdn.ckeditor.com/4.6.1/standard-all/contents.css', '/teamnote/css/editorStyle.css' ],
    // This is optional, but will let us define multiple different styles for multiple editors using the same CSS file.
    bodyClass: 'article-editor',
    // Reduce the list of block elements listed in the Format dropdown to the most commonly used.
    format_tags: 'p;h1;h2;h3;h4;h5;h6',
    // Simplify the Image and Link dialog windows. The "Advanced" tab is not needed in most cases.
    removeDialogTabs: 'image:advanced;link:advanced',
    // Define the list of styles which should be available in the Styles dropdown list.
    // If the "class" attribute is used to style an element, make sure to define the style for the class in "mystyles.css"
    // (and on your website so that it rendered in the same way).
    // Note: by default CKEditor looks for styles.js file. Defining stylesSet inline (as below) stops CKEditor from loading
    // that file, which means one HTTP request less (and a faster startup).
    // For more information see http://docs.ckeditor.com/#!/guide/dev_styles
    stylesSet: [
        /* Inline Styles */
        { name: 'Marker',			element: 'span', attributes: { 'class': 'marker' } },
        { name: 'Cited Work',		element: 'cite' },
        { name: 'Inline Quotation',	element: 'q' },
        /* Object Styles */
        {
            name: 'Special Container',
            element: 'div',
            styles: {
                padding: '5px 10px',
                background: '#eee',
                border: '1px solid #ccc'
            }
        },
        {
            name: 'Compact table',
            element: 'table',
            attributes: {
                cellpadding: '5',
                cellspacing: '0',
                border: '1',
                bordercolor: '#ccc'
            },
            styles: {
                'border-collapse': 'collapse'
            }
        },
        { name: 'Borderless Table',		element: 'table',	styles: { 'border-style': 'hidden', 'background-color': '#E6E6FA' } },
        { name: 'Square Bulleted List',	element: 'ul',		styles: { 'list-style-type': 'square' } },
        /* Widget Styles */
        // We use this one to style the brownie picture.
        { name: 'Illustration', type: 'widget', widget: 'image', attributes: { 'class': 'image-illustration' } },
        // Media embed
        { name: '240p', type: 'widget', widget: 'embedSemantic', attributes: { 'class': 'embed-240p' } },
        { name: '360p', type: 'widget', widget: 'embedSemantic', attributes: { 'class': 'embed-360p' } },
        { name: '480p', type: 'widget', widget: 'embedSemantic', attributes: { 'class': 'embed-480p' } },
        { name: '720p', type: 'widget', widget: 'embedSemantic', attributes: { 'class': 'embed-720p' } },
        { name: '1080p', type: 'widget', widget: 'embedSemantic', attributes: { 'class': 'embed-1080p' } }
    ]
} );