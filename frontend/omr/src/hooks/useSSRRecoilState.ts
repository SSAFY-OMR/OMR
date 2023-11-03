import { useEffect, useState } from 'react';

import { useRecoilState } from 'recoil';

export function useSSRRecoilState<T>(recoilState: any, defaultValue: T) {
  const [isInitial, setIsInitial] = useState(true);
  const [value, setValue] = useRecoilState(recoilState);

  useEffect(() => {
    setIsInitial(false);
  }, []);

  return [isInitial ? defaultValue : value, setValue] as const;
}
