
interface IOPipeConfig {
  debug?: boolean;
  token?: string;
}
interface Mark {
    start(s: string): void;
    end(s: string): void;
}

declare module "@iopipe/iopipe" {
    export function label(s: string): void;
    export let mark: Mark

    export default function iopipe(config?: IOPipeConfig): Function;
}