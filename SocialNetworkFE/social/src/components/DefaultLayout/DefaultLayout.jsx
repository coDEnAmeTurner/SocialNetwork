import React, { useContext, useState } from 'react'
import SideBar from '../Feed/SideBar/FeedSideBar';
import FeedHeader from '../Feed/Header/FeedHeader';
import FeedNavBar from '../Feed/FeedNavBar/FeedNavBar';
import ChatOverview from '../ChatOverview/ChatOverview';
import { IsOpenContext,  OpenMsgContext } from '../../configs/Contexts';

  export const DefaultLayout = ({children}) => {

  const [isForEdit,setIsForEdit] = useState(false);
  const [isOpen, isOpenDispatch] = useContext(IsOpenContext);
  const [openMsg, openMsgDispatch] = useContext(OpenMsgContext);

  return (
    <div className=''>
        <div className=''>{
            <>
          {
            !isForEdit ?
              <SideBar />:
              <></>
          }
          {!openMsg ? (
            <section
              className={`${isOpen && !isForEdit ? "feed-container-opened" : "feed-container"
                }`}
            >
              {
                !isForEdit ? <>
                  <FeedHeader />
                  <FeedNavBar />
                </> :
                  <></>
              }
              {children}
            </section>

          ) : (
            <section
              className={`${isOpen ? "feed-container-opened" : "feed-container"
                }`}
            >
              <FeedHeader />
              <ChatOverview />
            </section>

          )}

        </>
}
   </div>
    </div>
  )
}
