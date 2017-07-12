/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    // 工具栏配置
    config.toolbarGroups = [
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'forms', groups: [ 'forms' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        '/',
        { name: 'links', groups: [ 'links' ] },
        { name: 'insert', groups: [ 'insert' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'styles', groups: [ 'styles' ] },
        '/',
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];
    config.skin = 'bootstrapck';
    config.removeButtons = 'Source,Form,Checkbox,Radio,TextField,Textarea,Select,Button,' +
        'HiddenField,CreateDiv,Anchor,Flash,Iframe,ShowBlocks,About,Language,Copy,Cut,Paste,' +
        'PasteText,PasteFromWord,BidiLtr,BidiRtl,Indent,Outdent,PageBreak,Subscript,Superscript';
    // 代码块
    config.codeSnippet_theme = 'github';
    // 添加字体
    config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;华文彩云/华文彩云;微软雅黑/微软雅黑;'+ config.font_names;
    // 不用的附件
    config.removePlugins = 'elementspath';
    //可选字体大小
    config.fontSize_sizes='8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px'
    // 标题大小可选格式
    config.format_tags = 'p;h1;h2;h3;h4;h5;h6';
    // 数学公式
    config.mathJaxLib = '//cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.0/MathJax.js?config=TeX-AMS_HTML';
    // 去除结尾的<p> </p>
    config.enterMode = CKEDITOR.ENTER_BR;
    config.shiftEnterMode = CKEDITOR.ENTER_P;
    config.height = '70%';
    config.tabSpaces = 4;
};
