<div id="rshow" class="modal hide fade">

	<div class="modal-header">
		<button class="close" data-dismiss="modal"
			onclick="">×</button>
		<h2>
			<span id="span_title"></span>
		</h2>

	</div>
	<div class="modal-body">

		<div class="row-fluid">


			<div class="span12">


				<div id='tab_1'>
					<div id="tab_head"></div>

					<div class="tab-content" id="tab_content"></div>
					
				</div>
			</div>
		</div>


	</div>
	<div class="modal-footer">



			<input type="hidden" id="rshowid" value="" name="rshowid"/>

<#if suser??>
			<a class="btn btn-info" href="book?action=queryall&cur=0"
				>借阅 </a>	</#if>
	</div>
</div>



